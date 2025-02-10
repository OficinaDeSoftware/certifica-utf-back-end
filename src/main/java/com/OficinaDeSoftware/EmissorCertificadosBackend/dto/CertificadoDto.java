package com.OficinaDeSoftware.EmissorCertificadosBackend.dto;


import lombok.Data;

import java.util.List;

@Data
public class CertificadoDto {

    private Integer modelId;

    private String complement;

    private List<CertificateResponsibleDto> responsible;

}
