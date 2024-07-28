package kz.company.s_storage_service.services.database.impl;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import kz.company.s_storage_service.configs.MinioConfig;
import kz.company.s_storage_service.services.database.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class MinioService implements StorageService {
    private final MinioConfig config;
    private final MinioClient minioClient;
    @Value("${minio.put-object-part-size}")
    private Long putObjectPartSize;

    @Override
    public void save(String bucketName, String objectName, InputStream inputStream, String contentType) {
        try {
            minioClient.putObject(PutObjectArgs
                    .builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .stream(inputStream, inputStream.available(), -1)
                    .contentType(contentType)
                    .build());
        } catch (Exception e) {
            log.error("MinioService.uploadFile error: [{}]", e.getMessage(), e);
        }
    }

    @Override
    public void save(MultipartFile file, UUID uuid) throws Exception {
        minioClient.putObject(
                PutObjectArgs
                        .builder()
                        .bucket(config.getBucketName())
                        .object(uuid.toString())
                        .stream(file.getInputStream(), file.getSize(), putObjectPartSize)
                        .build()
        );
    }

    @Override
    public InputStream get(UUID uuid, long offset, long length) {
        try {
            return minioClient.getObject(
                    GetObjectArgs
                            .builder()
                            .bucket(config.getBucketName())
                            .offset(offset)
                            .length(length)
                            .object(uuid.toString())
                            .build());
        } catch (Exception e) {
            log.error("MinioService.getInputStream error: [{}]", e.getMessage(), e);
        }
        return InputStream.nullInputStream();
    }
}