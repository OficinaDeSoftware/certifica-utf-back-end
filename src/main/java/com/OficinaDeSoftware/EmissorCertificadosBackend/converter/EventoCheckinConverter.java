package com.OficinaDeSoftware.EmissorCertificadosBackend.converter;

import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.OficinaDeSoftware.EmissorCertificadosBackend.domain.EventCheckin;
import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.EventCheckinDto;

@Component
public class EventoCheckinConverter {

  @Autowired
  private ModelMapper modelMapper;


  public EventCheckinDto convertToDto(final EventCheckin user ) {

    return Objects.isNull(user) ? null : modelMapper.map( user, EventCheckinDto.class );

  } 

  public EventCheckin convertToEntity(final EventCheckinDto dto ) {

    return Objects.isNull(dto) ? null : modelMapper.map( dto, EventCheckin.class );
    
  }

}