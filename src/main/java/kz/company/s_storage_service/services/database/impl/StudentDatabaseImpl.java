package kz.company.s_storage_service.services.database.impl;

import kz.company.s_storage_service.models.dto.StudentDto;
import kz.company.s_storage_service.models.enitty.Student;
import kz.company.s_storage_service.repositories.StudentRepository;
import kz.company.s_storage_service.services.database.StudentDatabase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentDatabaseImpl implements StudentDatabase {
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Override
    public Optional<StudentDto> findById(Long id) {
        return studentRepository.findById(id).map(student -> modelMapper.map(student, StudentDto.class));
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
        return Optional.of(modelMapper.map(
                studentRepository.save(
                        modelMapper.map(dto, Student.class)), StudentDto.class));
    }

    private Optional<StudentDto> update(Student student, StudentDto dto) {
        modelMapper.map(dto, student);
        studentRepository.saveAndFlush(student);
        return Optional.of(modelMapper.map(student, StudentDto.class));
    }

    @Override
    public List<StudentDto> findAll() {
        return studentRepository.findAll().stream().map(student -> modelMapper.map(student, StudentDto.class)).toList();
    }

    @Override
    public Optional<StudentDto> findByRecordBookNumber(String recordBookNumber) {
        return studentRepository.findByRecordBookNumber(recordBookNumber).map(student -> modelMapper.map(student, StudentDto.class));
    }

}
