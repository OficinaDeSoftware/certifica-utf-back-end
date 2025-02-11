package com.OficinaDeSoftware.EmissorCertificadosBackend.domain;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "event_checkin")
public class EventCheckin {
    
    @Id 
    private String idEventCheckin;

    private LocalDateTime dhCheckin;

    private LocalDateTime dhCheckout;

    private String nrUuidParticipant;

    private String idEvent;

    private int idDateEvent;
}
