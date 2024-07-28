package kz.company.s_storage_service.exceptions;

public class StorageException extends RuntimeException {

    public StorageException(Exception ex) {
        super(ex);
    }
}