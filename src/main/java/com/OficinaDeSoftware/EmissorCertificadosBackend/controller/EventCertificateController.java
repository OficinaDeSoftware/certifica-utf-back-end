package com.OficinaDeSoftware.EmissorCertificadosBackend.controller;

import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.CertificateParticipantDto;
import com.OficinaDeSoftware.EmissorCertificadosBackend.service.CertificateParticipantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/event/certificate")
public class EventCertificateController {

    private final CertificateParticipantService certificateParticipantService;

    public EventCertificateController(CertificateParticipantService certificateParticipantService) {
        this.certificateParticipantService = certificateParticipantService;
    }

    @GetMapping("/{nrUuidParticipant}")
    public ResponseEntity<List<CertificateParticipantDto>> certificatesByParticipant( @PathVariable String nrUuidParticipant ) {
        return ResponseEntity.ok().body( certificateParticipantService.certificatesByParticipant( nrUuidParticipant ) );
    }

}
