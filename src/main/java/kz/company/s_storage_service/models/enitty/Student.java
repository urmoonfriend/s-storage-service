package kz.company.s_storage_service.models.enitty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Entity(name = "students")
@Accessors(chain = true)
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String fatherName;
    private Integer age;
    private String recordBookNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
