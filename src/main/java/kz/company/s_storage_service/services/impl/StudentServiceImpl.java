package kz.company.s_storage_service.services.impl;

import kz.company.s_storage_service.services.StudentService;
import kz.company.s_storage_service.services.database.StudentDatabase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import kz.company.s_storage_service.models.dto.*;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService {
    private final StudentDatabase studentDatabase;
    private final ModelMapper modelMapper;

    @Override
    public List<StudentDto> getAllUnits() {
        return studentDatabase.findAll();
    }

    @Override
    public Optional<StudentDto> getOneUnit(String recordBookNumber) {
        return studentDatabase.findByRecordBookNumber(recordBookNumber);
    }
}
