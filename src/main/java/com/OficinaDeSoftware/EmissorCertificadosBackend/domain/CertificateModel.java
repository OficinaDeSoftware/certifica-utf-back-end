package com.OficinaDeSoftware.EmissorCertificadosBackend.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "certificadomodelo")
public class CertificateModel {
    
    @Id
    private String idCertificadoModelo;
    private String xmlModelo;
}
