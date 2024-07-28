package kz.company.s_storage_service.services.database;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

public interface StorageService {
    void save(String bucketName, String objectName, InputStream inputStream, String contentType);

    void save(MultipartFile file, UUID uuid) throws Exception;

    InputStream get(UUID uuid, long offset, long length);
}
