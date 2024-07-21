package kz.company.s_storage_service.services.database.impl;

import kz.company.s_storage_service.models.dto.StudentDto;
import kz.company.s_storage_service.models.enitty.Student;
import kz.company.s_storage_service.repositories.StudentRepository;
import kz.company.s_storage_service.services.database.StudentDatabase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return studentRepository.findById(id).map(
                student -> modelMapper.map(student, StudentDto.class)
        );
    }

    @Override
    public Optional<StudentDto> create(StudentDto dto) {
        Optional<StudentDto> result = Optional.empty();
        try {
            Student student = modelMapper.map(dto, Student.class);
            result = Optional.of(
                    modelMapper.map(
                            studentRepository.saveAndFlush(student),
                            StudentDto.class));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return result;
    }

    @Override
    @Transactional
    public Optional<StudentDto> update(StudentDto dto) {
        Optional<StudentDto> result = Optional.empty();
        try {
            Optional<StudentDto> studentOptional = findById(dto.getId());
            if (studentOptional.isPresent()) {
                Student student = modelMapper.map(dto, Student.class);
                result = Optional.of(
                        modelMapper.map(
                                studentRepository.saveAndFlush(student),
                                StudentDto.class)
                );
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return result;
    }

    @Override
    public List<StudentDto> findAll() {
        return studentRepository.findAll()
                .stream()
                .map(student -> modelMapper.map(student, StudentDto.class))
                .toList();
    }

    @Override
    public Optional<StudentDto> findByRecordBookNumber(String recordBookNumber) {
        return studentRepository.findByRecordBookNumber(recordBookNumber)
                .map(student -> modelMapper.map(student, StudentDto.class));
    }

}
