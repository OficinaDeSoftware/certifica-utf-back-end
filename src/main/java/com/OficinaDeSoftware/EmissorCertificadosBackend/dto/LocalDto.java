package com.OficinaDeSoftware.EmissorCertificadosBackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocalDto {
    
    @Id
    private String idLocal;

    @JsonProperty("auditoriums")
    private String dsAuditorio;

    @JsonProperty("roomBlock")
    private String dsBloco;

    @JsonProperty("room")
    private String dsSala;

    @JsonProperty("capacity")
    private Integer qtdParticipantes;
}
