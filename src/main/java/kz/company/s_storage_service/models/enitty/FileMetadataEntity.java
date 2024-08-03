package kz.company.s_storage_service.models.enitty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.Accessors;

@Data
@Entity(name = "files")
@Accessors(chain = true)
public class FileMetadataEntity {

    @Id
    private String id;

    private long size;

    private String name;

    @Column(name = "content_type")
    private String httpContentType;
}