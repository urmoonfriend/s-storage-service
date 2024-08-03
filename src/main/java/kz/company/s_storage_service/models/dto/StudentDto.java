package kz.company.s_storage_service.models.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import kz.company.s_storage_service.configs.LocalDateTimeAdapter;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@XmlRootElement(name = "studentDto")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@Accessors(chain = true)
public class StudentDto {
    @XmlElement(name = "id")
    private Long id;
    @XmlElement(name = "firstName")
    private String firstName;
    @XmlElement(name = "lastName")
    private String lastName;
    @XmlElement(name = "fatherName")
    private String fatherName;
    @XmlElement(name = "file")
    private byte[] file;
    @XmlElement(name = "fileName")
    private String fileName;
    @XmlElement(name = "contentType")
    private String contentType;
    @XmlElement(name = "age")
    private Integer age;
    @XmlElement(name = "recordBookNumber")
    private String recordBookNumber;
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    @XmlElement(name = "createdAt")
    private LocalDateTime createdAt;
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    @XmlElement(name = "updatedAt")
    private LocalDateTime updatedAt;
}