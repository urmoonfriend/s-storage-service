package kz.company.s_storage_service.stream;

import kz.company.s_storage_service.endpoints.StudentEndpoint;
import kz.company.s_storage_service.models.dto.GetAllUnitsRequest;
import kz.company.s_storage_service.models.dto.GetOneUnitRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
@Slf4j
public class StreamReceiver {
    private final StudentEndpoint stundentEndpoint;
    private final StreamTransmitter streamTransmitter;
    private static final String ONE_STUDENT = "storageOneStudentResponse";
    private static final String ALL_STUDENTS = "storageAllStudentsResponse";

    @Bean
    public Consumer<GetOneUnitRequest> receiveStorageOneStudentRequest() {
        return request -> {
            log.info("receiveStorageOneStudentRequest request: {}", request);
            var response = stundentEndpoint.getOneUnit(request.getRecordBookNumber());
            streamTransmitter.sendRabbitMqMessage(ONE_STUDENT,
                    response.setCorrelationId(request.getCorrelationId()));
        };
    }

    @Bean
    public Consumer<GetAllUnitsRequest> receiveStorageAllStudentsRequest() {
        return request -> {
            log.info("receiveStorageAllStudentsRequest request: {}", request);
            var response = stundentEndpoint.getAllUnits();
            streamTransmitter.sendRabbitMqMessage(ALL_STUDENTS,
                    response.setCorrelationId(request.getCorrelationId()));
        };
    }
}
