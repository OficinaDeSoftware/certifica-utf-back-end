package com.OficinaDeSoftware.EmissorCertificadosBackend.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "location")
public class Location {

    private String address;
    private String complement;
    private Integer capacity;
    private LocationCordinates coordinates;
}
