package com.OficinaDeSoftware.EmissorCertificadosBackend.converter;

import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.OficinaDeSoftware.EmissorCertificadosBackend.domain.Certificate;
import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.CertificadoDto;

@Component
public class CertificadoConverter {

  @Autowired
  private ModelMapper modelMapper;

  public CertificadoDto convertToDto( final Certificate user ) {

    return Objects.isNull(user) ? null : modelMapper.map( user, CertificadoDto.class );

  } 

  public Certificate convertToEntity(final CertificadoDto dto ) {

    return Objects.isNull(dto) ? null : modelMapper.map( dto, Certificate.class );
    
  }

}