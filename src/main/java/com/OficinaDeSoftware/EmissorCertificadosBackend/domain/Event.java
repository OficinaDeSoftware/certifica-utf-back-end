package com.OficinaDeSoftware.EmissorCertificadosBackend.domain;

import java.util.List;
import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "event")
public class Event {
    
    @Id
    private String idEvent;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer workload;
    private String description;
    private String nrUuidResponsible;
    private Location location;
    private List<DateEvent> dates;
    private Certificate certificate;

//    @Transient
//    List<User> participantes;

//    @Transient
//    private Certificado certificado;

}
