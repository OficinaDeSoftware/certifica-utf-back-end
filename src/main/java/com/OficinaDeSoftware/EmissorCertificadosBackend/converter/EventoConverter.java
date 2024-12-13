package com.OficinaDeSoftware.EmissorCertificadosBackend.converter;

import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.OficinaDeSoftware.EmissorCertificadosBackend.domain.Evento;
import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.EventoDto;

@Component
public class EventoConverter {

  @Autowired
  private ModelMapper modelMapper;

  public EventoDto convertToDto( final Evento user ) {

    return Objects.isNull(user) ? null : modelMapper.map( user, EventoDto.class );

  } 

  public Evento convertToEntity( final EventoDto dto ) {

    return Objects.isNull(dto) ? null : modelMapper.map( dto, Evento.class );
    
  }

}