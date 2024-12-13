package com.OficinaDeSoftware.EmissorCertificadosBackend.converter;

import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.OficinaDeSoftware.EmissorCertificadosBackend.domain.EventoCheckin;
import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.EventoCheckinDto;

@Component
public class EventoCheckinConverter {

  @Autowired
  private ModelMapper modelMapper;


  public EventoCheckinDto convertToDto( final EventoCheckin user ) {

    return Objects.isNull(user) ? null : modelMapper.map( user, EventoCheckinDto.class );

  } 

  public EventoCheckin convertToEntity( final EventoCheckinDto dto ) {

    return Objects.isNull(dto) ? null : modelMapper.map( dto, EventoCheckin.class );
    
  }

}