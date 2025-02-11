package com.OficinaDeSoftware.EmissorCertificadosBackend.converter;

import com.OficinaDeSoftware.EmissorCertificadosBackend.domain.CertificateParticipant;
import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.CertificateParticipantDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class CertificateParticipantConverter {

    private final ModelMapper modelMapper;

    public CertificateParticipantConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<CertificateParticipantDto> toDto( List<CertificateParticipant> certificateParticipants ) {
        return certificateParticipants.stream().map( this::toDto ).toList();
    }

    public CertificateParticipantDto toDto( CertificateParticipant certificateParticipant ) {
        return Objects.isNull( certificateParticipant ) ? null : modelMapper.map( certificateParticipant, CertificateParticipantDto.class );
    }
}
