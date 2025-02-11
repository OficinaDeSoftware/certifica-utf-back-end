package com.OficinaDeSoftware.EmissorCertificadosBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CertificateParticipantDto {

    private int id;

    private String idEvent;

    private String nrUuidParticipant;

    private String certificateUrl;

}
