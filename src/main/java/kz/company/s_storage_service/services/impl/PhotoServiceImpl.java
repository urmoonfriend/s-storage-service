package kz.company.s_storage_service.services.impl;

import kz.company.s_storage_service.exceptions.StorageException;
import kz.company.s_storage_service.models.enitty.FileMetadataEntity;
import kz.company.s_storage_service.models.minio.ChunkWithMetadata;
import kz.company.s_storage_service.models.minio.Range;
import kz.company.s_storage_service.repositories.FileMetadataRepository;
import kz.company.s_storage_service.services.PhotoService;
import kz.company.s_storage_service.services.database.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PhotoServiceImpl implements PhotoService {
    private final StorageService storageService;

    private final FileMetadataRepository fileMetadataRepository;

    @Override
    @Transactional
    public FileMetadataEntity save(MultipartFile multipartFile) {
        try {
            UUID fileUuid = UUID.randomUUID();
            FileMetadataEntity metadata = new FileMetadataEntity()
                    .setId(fileUuid.toString())
                    .setName(multipartFile.getName())
                    .setSize(multipartFile.getSize())
                    .setHttpContentType(multipartFile.getContentType());
            storageService.save(multipartFile, fileUuid);
            return fileMetadataRepository.save(metadata);
        } catch (Exception ex) {
            log.error("PhotoServiceImpl.save error: [{}]", ex.getMessage(), ex);
            throw new StorageException(ex);
        }
    }

    @Override
    public ChunkWithMetadata fetchChunk(UUID uuid, Range range) {
        FileMetadataEntity fileMetadata = fileMetadataRepository.findById(uuid.toString()).orElseThrow();
        return new ChunkWithMetadata(fileMetadata, readChunk(uuid, range, fileMetadata.getSize()));
    }

    private byte[] readChunk(UUID uuid, Range range, long fileSize) {
        long startPosition = range.getRangeStart();
        long endPosition = range.getRangeEnd(fileSize);
        int chunkSize = (int) (endPosition - startPosition + 1);
        try (InputStream inputStream = storageService.get(uuid, startPosition, chunkSize)) {
            return inputStream.readAllBytes();
        } catch (Exception e) {
            log.error("PhotoServiceImpl.readChunk uuid: [{}], error: [{}]", uuid, e.getMessage(), e);
            throw new StorageException(e);
        }
    }

}
