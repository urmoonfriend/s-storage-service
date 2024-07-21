package kz.company.s_storage_service.services.database;

import kz.company.s_storage_service.models.dto.StudentDto;

import java.util.List;
import java.util.Optional;

public interface StudentDatabase {
    Optional<StudentDto> findById(Long id);

    Optional<StudentDto> create(StudentDto dto);

    Optional<StudentDto> update(StudentDto dto);

    List<StudentDto> findAll();

    Optional<StudentDto> findByRecordBookNumber(String recordBookNumber);
}
