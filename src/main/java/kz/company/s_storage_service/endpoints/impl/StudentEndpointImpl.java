package kz.company.s_storage_service.endpoints.impl;

import jakarta.jws.WebService;
import kz.company.s_storage_service.endpoints.StudentEndpoint;
import kz.company.s_storage_service.enums.ResultCode;
import kz.company.s_storage_service.models.dto.ResultMessage;
import kz.company.s_storage_service.models.dto.StudentDto;
import kz.company.s_storage_service.services.database.StudentDatabase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@WebService(
        serviceName = "Student",
        portName = "StudentPort",
        targetNamespace = "http://service.ws.sample/",
        endpointInterface = "kz.company.s_storage_service.endpoints.StudentEndpoint")
@Service
@RequiredArgsConstructor
@Slf4j
public class StudentEndpointImpl implements StudentEndpoint {

    private final StudentDatabase studentDatabase;

    @Override
    public ResultMessage createOrUpdateStudent(StudentDto studentDto) {
        ResultMessage resultMessage = ResultMessage.error(ResultCode.SERVER_ERROR.name());
        Optional<StudentDto> studentDtoOptional = studentDatabase.createOrUpdateById(studentDto);
        log.info("StudentEndpointImpl.createStudent: createdStudent: [{}]", studentDtoOptional);
        if (studentDtoOptional.isPresent()) {
            resultMessage = ResultMessage.success(studentDtoOptional.get());
        }
        log.info("StudentEndpointImpl.createStudent: beforeResponse: [{}]", resultMessage);
        return resultMessage;
    }

    @Override
    public ResultMessage getAllUnits() {
        ResultMessage response = ResultMessage.success(studentDatabase.findAll());
        log.info("StudentEndpointImpl.getAllUnits: response: [{}]", response);
        return response;
    }

    @Override
    public ResultMessage getOneUnit(String recordBookNumber) {
        log.info("StudentEndpointImpl.getOneUnit request: [{}]", recordBookNumber);
        ResultMessage response = studentDatabase.findByRecordBookNumber(recordBookNumber)
                .map(ResultMessage::success)
                .orElseGet(() -> ResultMessage.error(ResultCode.NOT_FOUND.name()));
        log.info("StudentEndpointImpl.getOneUnit response: [{}]", response);
        return response;
    }
}
