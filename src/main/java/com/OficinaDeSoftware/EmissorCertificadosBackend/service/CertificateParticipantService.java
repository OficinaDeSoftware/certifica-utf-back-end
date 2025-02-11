package com.OficinaDeSoftware.EmissorCertificadosBackend.service;

import com.OficinaDeSoftware.EmissorCertificadosBackend.converter.CertificateParticipantConverter;
import com.OficinaDeSoftware.EmissorCertificadosBackend.domain.CertificateParticipant;
import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.CertificateParticipantDto;
import com.OficinaDeSoftware.EmissorCertificadosBackend.repository.CertificateParticipantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertificateParticipantService {

    final CertificateParticipantRepository repository;
    final CertificateParticipantConverter converter;

    public CertificateParticipantService(
            CertificateParticipantRepository repository,
            CertificateParticipantConverter converter
    ) {
        this.repository = repository;
        this.converter = converter;
    }

    public void insert( CertificateParticipant certificateParticipant ) {
        repository.insert( certificateParticipant );
    }

    public List<CertificateParticipantDto> certificatesByParticipant( final String nrUuidParticipant ) {
        return converter.toDto( repository.findAllByNrUuidParticipant( nrUuidParticipant ) );
    }

}
