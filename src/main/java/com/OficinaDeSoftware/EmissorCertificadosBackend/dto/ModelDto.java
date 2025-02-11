package com.OficinaDeSoftware.EmissorCertificadosBackend.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModelDto {

    private String id;

    private String html;

    private String previewUrl;

}
