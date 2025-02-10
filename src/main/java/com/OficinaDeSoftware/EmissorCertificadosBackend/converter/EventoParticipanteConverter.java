package com.OficinaDeSoftware.EmissorCertificadosBackend.converter;

import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.OficinaDeSoftware.EmissorCertificadosBackend.domain.EventParticipant;
import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.EventParticipantDto;

@Component
public class EventoParticipanteConverter {

  @Autowired
  private ModelMapper modelMapper;


  public EventParticipantDto convertToDto(final EventParticipant user ) {

    return Objects.isNull(user) ? null : modelMapper.map( user, EventParticipantDto.class );

  } 

  public EventParticipant convertToEntity(final EventParticipantDto dto ) {

    return Objects.isNull(dto) ? null : modelMapper.map( dto, EventParticipant.class );
    
  }

}