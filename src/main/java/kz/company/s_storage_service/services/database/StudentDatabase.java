package kz.company.s_storage_service.services.database;

import java.util.List;
import java.util.Optional;
import kz.company.s_storage_service.models.dto.*;

public interface StudentDatabase {
    Optional<StudentDto> findById(Long id);

    Optional<StudentDto> createOrUpdateById(StudentDto dto);

    List<StudentDto> findAll();

    Optional<StudentDto> findByRecordBookNumber(String recordBookNumber);
}
