package kz.company.s_storage_service.endpoints;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;
import kz.company.s_storage_service.models.dto.ResultMessage;
import kz.company.s_storage_service.models.dto.StudentDto;

@WebService(targetNamespace = "http://service.ws.sample/", name = "StudentEndpoint")
public interface StudentEndpoint {

    @WebMethod
    @WebResult(name = "createStudentResponse")
    @RequestWrapper(
            localName = "createStudent",
            targetNamespace = "http://service.ws.sample/",
            className = "kz.company.s_storage_service.endpoints.CreateStudentRequest")
    @ResponseWrapper(
            localName = "ResultMessage",
            targetNamespace = "http://localhost:8086/xml/school",
            className = "kz.company.s_storage_service.models.dto.ResultMessage")
    ResultMessage createStudent(@WebParam(name = "studentDto") StudentDto studentDto);

    //TODO fix serialization issue with ResultMessage (now return always success:false)
}
