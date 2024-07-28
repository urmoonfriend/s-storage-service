package kz.company.s_storage_service.configs;

import jakarta.xml.ws.Endpoint;
import kz.company.s_storage_service.endpoints.HelloEndpoint;
import kz.company.s_storage_service.endpoints.StorageEndpoint;
import kz.company.s_storage_service.endpoints.StudentEndpoint;
import lombok.RequiredArgsConstructor;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class WebServiceConfig {
    private final Bus bus;
    private final HelloEndpoint helloService;
    private final StudentEndpoint studentService;
    private final StorageEndpoint storageEndpoint;

    @Bean
    public Endpoint helloEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, helloService);
        endpoint.publish("/HelloService");
        return endpoint;
    }

    @Bean
    public Endpoint studentEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, studentService);
        endpoint.publish("/StudentService");
        return endpoint;
    }

    @Bean
    public Endpoint storageEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, storageEndpoint);
        endpoint.publish("/StorageService");
        return endpoint;
    }
}