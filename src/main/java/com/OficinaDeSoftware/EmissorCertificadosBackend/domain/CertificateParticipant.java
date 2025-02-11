package com.OficinaDeSoftware.EmissorCertificadosBackend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document( value = "certificate_participant")
public class CertificateParticipant {

    @Id
    private String id;

    private String idEvent;

    private String nrUuidParticipant;

    private String certificateUrl;

}
