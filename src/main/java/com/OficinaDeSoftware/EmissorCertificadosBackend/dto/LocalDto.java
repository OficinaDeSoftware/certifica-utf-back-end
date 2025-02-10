package com.OficinaDeSoftware.EmissorCertificadosBackend.dto;

import lombok.Data;

@Data
public class LocalDto {

    private String address;

    private String complement;

    private Integer capacity;

    private LocationCordinatesDto coordinates;

}
