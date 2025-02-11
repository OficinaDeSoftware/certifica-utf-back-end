package com.OficinaDeSoftware.EmissorCertificadosBackend.dto;

import lombok.Data;

import java.util.List;

@Data
public class CertificateDto {

    private String modelId;

    private String complement;

    private String issuerLogoUrl;

    private List<CertificateResponsibleDto> responsible;

}
