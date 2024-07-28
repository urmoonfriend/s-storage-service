package kz.company.s_storage_service.models.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.experimental.Accessors;

@XmlRootElement(name = "ResultMessage")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@Accessors(chain = true)
public class ResultMessage {
    @XmlElement(name = "success")
    private boolean success;
    @XmlElement(name = "message")
    private Object message;
    @XmlElement(name = "error")
    private String error;

    public static ResultMessage success(StudentDto message) {
        return new ResultMessage()
                .setSuccess(true)
                .setMessage(message);
    }

    public static ResultMessage error(String error) {
        return new ResultMessage()
                .setSuccess(false)
                .setError(error);
    }
}