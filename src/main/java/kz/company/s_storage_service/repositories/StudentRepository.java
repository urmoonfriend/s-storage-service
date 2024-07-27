package kz.company.s_storage_service.repositories;

import kz.company.s_storage_service.models.enitty.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByRecordBookNumber(String recordBookNumber);
}
