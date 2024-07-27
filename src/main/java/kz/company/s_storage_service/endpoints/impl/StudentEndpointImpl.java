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
    public ResultMessage createStudent(StudentDto studentDto) {
        ResultMessage resultMessage = ResultMessage.error(ResultCode.SERVER_ERROR.name());
        Optional<StudentDto> studentDtoOptional = studentDatabase.create(studentDto);
        log.info("StudentEndpointImpl.createStudent: createdStudent: [{}]", studentDtoOptional);
        if(studentDtoOptional.isPresent()) {
            resultMessage = ResultMessage.success(studentDtoOptional.get());
        }
        log.info("StudentEndpointImpl.createStudent: beforeResponse: [{}]", resultMessage);
        return resultMessage;
    }
/*
    @Override
    public ResultMessage<StudentDto> updateStudent(StudentDto studentDto) {
        return studentDatabase.update(studentDto)
                .map(ResultMessage::success)
                .orElseGet(() -> ResultMessage.error(ResultCode.SERVER_ERROR.name()));
    }

 */
}