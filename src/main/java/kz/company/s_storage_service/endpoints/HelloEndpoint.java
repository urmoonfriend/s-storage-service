package kz.company.s_storage_service.endpoints;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;

@WebService(targetNamespace = "http://service.ws.sample/", name = "HelloEndpoint")
public interface HelloEndpoint {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(
            localName = "sayHello",
            targetNamespace = "http://service.ws.sample/",
            className = "sample.ws.service.RequestSayHello")
    @WebMethod(action = "urn:SayHello")
    @ResponseWrapper(
            localName = "sayHelloResponse",
            targetNamespace = "http://service.ws.sample/",
            className = "sample.ws.service.SayHelloResponse")
    String sayHello(@WebParam(name = "name", targetNamespace = "") String name);
}
