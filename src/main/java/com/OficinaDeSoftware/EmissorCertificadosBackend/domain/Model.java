package com.OficinaDeSoftware.EmissorCertificadosBackend.domain;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "modelo")
public class Model {

    @Id
    private String idModelo;
    private String htmlModelo;
    private String previewUrl;
}