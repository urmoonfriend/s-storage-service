package kz.company.s_storage_service.models.minio;

import org.jetbrains.annotations.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class CustomMultipartFile implements MultipartFile {

    private final String fileName;
    private final byte[] fileContent;
    private final String contentType;

    public CustomMultipartFile(String fileName, byte[] fileContent, String contentType) {
        this.fileName = fileName;
        this.fileContent = fileContent;
        this.contentType = contentType;
    }

    @NotNull
    @Override
    public String getName() {
        return fileName;
    }

    @Override
    public String getOriginalFilename() {
        return fileName;
    }

    @Override
    public String getContentType() {
        return contentType;
    }

    @Override
    public boolean isEmpty() {
        return fileContent.length == 0;
    }

    @Override
    public long getSize() {
        return fileContent.length;
    }

    @NotNull
    @Override
    public byte[] getBytes() {
        return fileContent;
    }

    @NotNull
    @Override
    public InputStream getInputStream() {
        return new ByteArrayInputStream(fileContent);
    }

    @Override
    public void transferTo(@NotNull File dest) throws IOException, IllegalStateException {
        try (FileOutputStream fos = new FileOutputStream(dest)) {
            fos.write(fileContent);
        }
    }
}