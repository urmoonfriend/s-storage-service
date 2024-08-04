package kz.company.s_storage_service.models.dto;

import jakarta.xml.bind.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@XmlRootElement(name = "ResultMessage")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@Accessors(chain = true)
public class ResultMessage {
    @XmlElement(name = "success")
    private boolean success;
    @XmlElement(name = "student")
    private StudentDto student;
    @XmlElementWrapper(name = "students")
    @XmlElement(name = "student")
    private List<StudentDto> students;
    @XmlElement(name = "error")
    private String error;
    private String correlationId;

    public static ResultMessage success(StudentDto message) {
        return new ResultMessage()
                .setSuccess(true)
                .setStudent(message);
    }

    public static ResultMessage success(List<StudentDto> message) {
        return new ResultMessage()
                .setSuccess(true)
                .setStudents(message);
    }

    public static ResultMessage error(String error) {
        return new ResultMessage()
                .setSuccess(false)
                .setError(error);
    }
}