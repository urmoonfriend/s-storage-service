package kz.company.s_storage_service.endpoints;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import kz.company.s_storage_service.models.dto.ResultMessage;
import kz.company.s_storage_service.models.dto.StudentDto;

@WebService(targetNamespace = "http://service.ws.sample/", name = "StudentEndpoint")
public interface StudentEndpoint {

    @WebMethod
    ResultMessage createOrUpdateStudent(@WebParam(name = "studentDto") StudentDto studentDto);

    @WebMethod
    ResultMessage getAllUnits();

    @WebMethod
    ResultMessage getOneUnit(@WebParam(name = "recordBookNumber") String recordBookNumber);
}
