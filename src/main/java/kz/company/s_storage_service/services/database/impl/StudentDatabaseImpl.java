package kz.company.s_storage_service.services.database.impl;

import kz.company.s_storage_service.models.dto.StudentDto;
import kz.company.s_storage_service.models.enitty.FileMetadataEntity;
import kz.company.s_storage_service.models.enitty.Student;
import kz.company.s_storage_service.models.minio.ChunkWithMetadata;
import kz.company.s_storage_service.models.minio.CustomMultipartFile;
import kz.company.s_storage_service.models.minio.Range;
import kz.company.s_storage_service.repositories.StudentRepository;
import kz.company.s_storage_service.services.PhotoService;
import kz.company.s_storage_service.services.database.StudentDatabase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentDatabaseImpl implements StudentDatabase {
    private final StudentRepository studentRepository;
    private final PhotoService photoService;
    private final ModelMapper modelMapper;
    @Value("${app.minio.streaming.default-chunk-size}")
    public Integer defaultChunkSize;

    @Override
    public Optional<StudentDto> findById(Long id) {
        return studentRepository.findById(id)
                .map(this::mapToDtoWithFile);
    }

    @Override
    public Optional<StudentDto> createOrUpdateById(StudentDto dto) {
        log.info("StudentDatabaseImpl.createOrUpdateById request: [{}]", dto);
        Optional<StudentDto> result = Optional.empty();
        try {
            if (dto.getId() != null) {
                result = studentRepository.findById(dto.getId())
                        .map(student -> update(student, dto))
                        .orElseGet(() -> create(dto));
            } else {
                result = create(dto);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        log.info("StudentDatabaseImpl.createOrUpdateById response: [{}]", result);
        return result;
    }

    private Optional<StudentDto> create(StudentDto dto) {
        Student mappedStudent = modelMapper.map(dto, Student.class);
        Student student = studentRepository.saveAndFlush(
                setFileMetadataEntity(dto, mappedStudent));
        return Optional.of(modelMapper.map(student, StudentDto.class)
                .setFile(dto.getFile())
                .setFileName(dto.getFileName())
                .setContentType(dto.getContentType()));
    }

    private Student setFileMetadataEntity(StudentDto dto, Student student) {
        if (dto.getFileName() != null && dto.getFile() != null && dto.getContentType() != null) {
            FileMetadataEntity fileMetadata = photoService.save(
                    new CustomMultipartFile(dto.getFileName(), dto.getFile(), dto.getContentType()));
            student.setFileMetadata(fileMetadata);
        }
        return student;
    }

    private StudentDto mapToDtoWithFile(Student student) {
        StudentDto dto = modelMapper.map(student, StudentDto.class);
        if (student.getFileMetadata() != null) {
            Range parsedRange = Range.parseHttpRangeString(null, defaultChunkSize);
            ChunkWithMetadata chunkWithMetadata = photoService.fetchChunk(
                    UUID.fromString(student.getFileMetadata().getId()), parsedRange);
            dto.setFile(chunkWithMetadata.chunk())
                    .setFileName(chunkWithMetadata.metadata().getName())
                    .setContentType(chunkWithMetadata.metadata().getHttpContentType());
        }
        return dto;
    }

    private Optional<StudentDto> update(Student student, StudentDto dto) {
        modelMapper.map(dto, student);
        Student studentToUpdate = studentRepository.saveAndFlush(
                setFileMetadataEntity(dto, student));
        return Optional.of(modelMapper.map(studentToUpdate, StudentDto.class)
                .setFile(dto.getFile())
                .setFileName(dto.getFileName())
                .setContentType(dto.getContentType()));
    }

    @Override
    public List<StudentDto> findAll() {
        return studentRepository.findAll()
                .stream()
                .map(this::mapToDtoWithFile)
                .toList();
    }

    @Override
    public Optional<StudentDto> findByRecordBookNumber(String recordBookNumber) {
        return studentRepository.findByRecordBookNumber(recordBookNumber)
                .map(this::mapToDtoWithFile);
    }

}
