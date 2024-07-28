package kz.company.s_storage_service.configs;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class MinioConfig {
    @Value("${minio.url}")
    private String url;

    @Value("${minio.access.name}")
    private String accessKey;

    @Value("${minio.access.secret}")
    private String accessSecret;

    @Value("${minio.bucket.name}")
    private String bucketName;

    @Bean
    public MinioClient minioClient() throws Exception {
        MinioClient client = MinioClient.builder()
                .endpoint(url)
                .credentials(accessKey, accessSecret)
                .build();
        if (!client.bucketExists(BucketExistsArgs
                .builder()
                .bucket(bucketName)
                .build())) {
            client.makeBucket(
                    MakeBucketArgs
                            .builder()
                            .bucket(bucketName)
                            .build()
            );
        }
        return client;
    }
}