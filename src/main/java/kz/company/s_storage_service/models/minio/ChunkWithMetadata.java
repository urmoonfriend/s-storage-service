package kz.company.s_storage_service.models.minio;

import kz.company.s_storage_service.models.enitty.FileMetadataEntity;

public record ChunkWithMetadata(
            FileMetadataEntity metadata,
            byte[] chunk
    ) {
    }