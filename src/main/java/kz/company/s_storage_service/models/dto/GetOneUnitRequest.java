package kz.company.s_storage_service.models.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@XmlRootElement(name = "getOneUnit", namespace = "http://service.ws.sample/")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetOneUnitRequest {
    @XmlElement(name = "recordBookNumber")
    private String recordBookNumber;
    private String correlationId;
}