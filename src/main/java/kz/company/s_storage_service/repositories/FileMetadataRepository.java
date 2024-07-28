package kz.company.s_storage_service.repositories;

import kz.company.s_storage_service.models.enitty.FileMetadataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileMetadataRepository extends JpaRepository<FileMetadataEntity, String> {
}