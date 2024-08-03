package kz.company.s_storage_service.endpoints.impl;

import jakarta.jws.WebService;
import kz.company.s_storage_service.endpoints.StorageEndpoint;
import kz.company.s_storage_service.models.minio.ChunkWithMetadata;
import kz.company.s_storage_service.models.minio.CustomMultipartFile;
import kz.company.s_storage_service.models.minio.Range;
import kz.company.s_storage_service.services.PhotoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@WebService(
        serviceName = "Storage",
        portName = "StoragePort",
        targetNamespace = "http://service.ws.sample/",
        endpointInterface = "kz.company.s_storage_service.endpoints.StorageEndpoint"
)
@Service
@RequiredArgsConstructor
@Slf4j
public class StorageEndpointImpl implements StorageEndpoint {

    private final PhotoService photoService;

    @Value("${app.minio.streaming.default-chunk-size}")
    public Integer defaultChunkSize;

    @Override
    public UUID save(byte[] file, String fileName, String contentType) {
        try {
            MultipartFile multipartFile = new CustomMultipartFile(fileName, file, contentType);
            return UUID.fromString(photoService.save(multipartFile).getId());
        } catch (Exception e) {
            log.error("Error saving file", e);
            throw new RuntimeException("Error saving file", e);
        }
    }

    @Override
    public byte[] readChunk(String range, UUID uuid) {
        Range parsedRange = Range.parseHttpRangeString(range, defaultChunkSize);
        ChunkWithMetadata chunkWithMetadata = photoService.fetchChunk(uuid, parsedRange);
        return chunkWithMetadata.chunk();
    }
}