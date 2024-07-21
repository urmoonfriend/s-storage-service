package kz.company.s_storage_service.models.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class StudentDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String fatherName;
    private Integer age;
    private String recordBookNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}