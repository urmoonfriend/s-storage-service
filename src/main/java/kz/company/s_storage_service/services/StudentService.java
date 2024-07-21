package kz.company.s_storage_service.services;

import kz.company.s_storage_service.models.dto.StudentDto;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<StudentDto> getAllUnits();

    Optional<StudentDto> getOneUnit(String recordBookNumber);
}
