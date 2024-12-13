package com.OficinaDeSoftware.EmissorCertificadosBackend.domain;


import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "evento")
public class Evento {
    
    @Id
    private String idEvento;
    private String dsNome;
    private LocalDateTime dhInicio;
    private LocalDateTime dhFim;
    private Integer nrCargaHoraria;
    private String dsInformacoes;
    private String nrUuidResponsavel;
    private String idLocal;
    private Local local;

    @Transient
    MultipartFile backgroundImage;

    @Transient
    List<User> participantes;

    @Transient
    private List<DateEvent> dates;

    @Transient
    private Certificado certificado;

}
