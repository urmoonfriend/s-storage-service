package kz.company.s_storage_service.models.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@XmlRootElement(name = "getAllUnits", namespace = "http://service.ws.sample/")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetAllUnitsRequest {
    private String correlationId;
}