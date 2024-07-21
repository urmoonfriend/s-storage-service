package kz.company.s_storage_service.models.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ResultMessage<T> {
    private boolean success;
    private T message;
    private String error;

    public static <T> ResultMessage<T> success(T message) {
        return new ResultMessage<T>()
                .setSuccess(true)
                .setMessage(message);
    }

    public static <T> ResultMessage<T> error(String error) {
        return new ResultMessage<T>()
                .setSuccess(false)
                .setError(error);
    }
}
