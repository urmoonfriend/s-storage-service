package kz.company.s_storage_service.endpoints.impl;

import kz.company.s_storage_service.endpoints.HelloEndpoint;
import org.springframework.stereotype.Service;

import jakarta.jws.WebService;

@WebService(
        serviceName = "Hello",
        portName = "HelloPort",
        targetNamespace = "http://service.ws.sample/",
        endpointInterface = "kz.company.s_storage_service.endpoints.HelloEndpoint")
@Service
public class HelloServiceImpl implements HelloEndpoint {

    @Override
    public String sayHello(String name) {

        return "Hello " + name;
    }

}
