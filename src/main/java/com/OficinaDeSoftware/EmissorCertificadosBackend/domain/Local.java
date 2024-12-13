package com.OficinaDeSoftware.EmissorCertificadosBackend.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "local")
public class Local {
    
    @Id
    private UUID idLocal;

    private String dsAuditorio;
    private String dsBloco;
    private String dsSala;
    private Integer qtdParticipoantes;
}
