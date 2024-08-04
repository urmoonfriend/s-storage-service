package kz.company.s_storage_service.stream;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class StreamTransmitter {
    private final StreamBridge streamBridge;
    private static final String S_OUT_0 = "%s-out-0";

    public void sendRabbitMqMessage(String exchangePrefix, Object message) {
        streamBridge.send(String.format(S_OUT_0, exchangePrefix), message);
    }
}
