package kz.company.s_storage_service.services;

import kz.company.s_storage_service.models.minio.Range;
import kz.company.s_storage_service.models.minio.ChunkWithMetadata;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface PhotoService {
    @Transactional
    UUID save(MultipartFile multipartFile);

    ChunkWithMetadata fetchChunk(UUID uuid, Range range);
}
