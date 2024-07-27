package kz.company.s_storage_service.models.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@XmlRootElement(name = "studentDto")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@Accessors(chain = true)
public class StudentDto {
    @XmlElement
    private Long id;
    @XmlElement
    private String firstName;
    @XmlElement
    private String lastName;
    @XmlElement
    private String fatherName;
    @XmlElement
    private Integer age;
    @XmlElement
    private String recordBookNumber;
    @XmlElement
    private LocalDateTime createdAt;
    @XmlElement
    private LocalDateTime updatedAt;
}