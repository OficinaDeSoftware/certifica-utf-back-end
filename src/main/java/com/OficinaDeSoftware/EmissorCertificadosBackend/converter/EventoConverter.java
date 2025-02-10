package com.OficinaDeSoftware.EmissorCertificadosBackend.converter;

import java.util.Objects;

import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.request.EventRequestDto;
import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.response.EventBasicResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.OficinaDeSoftware.EmissorCertificadosBackend.domain.Event;
import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.response.EventResponseDto;

@Component
public class EventoConverter {

  @Autowired
  private ModelMapper modelMapper;

  public EventBasicResponseDto toBasicResponseDto( final Event event ) {
    return Objects.isNull( event ) ? null : modelMapper.map( event, EventBasicResponseDto.class );
  }

  public EventResponseDto convertToDto( final Event event ) {

    return Objects.isNull(event) ? null : modelMapper.map( event, EventResponseDto.class );

  } 

  public Event convertToEntity(final EventResponseDto dto ) {

    return Objects.isNull(dto) ? null : modelMapper.map( dto, Event.class );
    
  }

  public Event convertToEntity(final EventRequestDto dto ) {

    return Objects.isNull(dto) ? null : modelMapper.map( dto, Event.class );

  }

}