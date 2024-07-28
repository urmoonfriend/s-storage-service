package kz.company.s_storage_service.endpoints;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

import java.util.UUID;

@WebService(targetNamespace = "http://service.ws.sample/", name = "StorageEndpoint")
public interface StorageEndpoint {

    @WebMethod
    @WebResult(name = "savePhotoResponse")
    UUID save(@WebParam(name = "file") byte[] file, @WebParam(name = "fileName") String fileName, @WebParam(name = "contentType") String contentType);

    @WebMethod
    @WebResult(name = "readChunkResponse")
    byte[] readChunk(@WebParam(name = "range") String range, @WebParam(name = "uuid") UUID uuid);
}