package kz.company.s_storage_service.controllers;

import kz.company.s_storage_service.enums.ResultCode;
import kz.company.s_storage_service.models.dto.ResultMessage;
import kz.company.s_storage_service.models.dto.StudentDto;
import kz.company.s_storage_service.services.database.StudentDatabase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@Component
@RequiredArgsConstructor
public class StudentController {
    private static final String NAMESPACE_URI = "http://localhost:68080/students";
    private final StudentDatabase studentDatabase;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "StudentDto")
    @ResponsePayload
    public ResultMessage<StudentDto> create(@RequestPayload StudentDto request) {
        return studentDatabase.create(request)
                .map(ResultMessage::success)
                .orElseGet(() -> ResultMessage.error(ResultCode.SERVER_ERROR.name()));
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "StudentDto")
    @ResponsePayload
    public ResultMessage<StudentDto> update(@RequestPayload StudentDto request) {
        return studentDatabase.create(request)
                .map(ResultMessage::success)
                .orElseGet(() -> ResultMessage.error(ResultCode.SERVER_ERROR.name()));
    }
}
