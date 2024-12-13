package com.OficinaDeSoftware.EmissorCertificadosBackend.converter;

import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.OficinaDeSoftware.EmissorCertificadosBackend.domain.EventoParticipante;
import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.EventoParticipanteDto;

@Component
public class EventoParticipanteConverter {

  @Autowired
  private ModelMapper modelMapper;


  public EventoParticipanteDto convertToDto( final EventoParticipante user ) {

    return Objects.isNull(user) ? null : modelMapper.map( user, EventoParticipanteDto.class );

  } 

  public EventoParticipante convertToEntity( final EventoParticipanteDto dto ) {

    return Objects.isNull(dto) ? null : modelMapper.map( dto, EventoParticipante.class );
    
  }

}