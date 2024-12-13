package com.OficinaDeSoftware.EmissorCertificadosBackend.converter;

import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.OficinaDeSoftware.EmissorCertificadosBackend.domain.CertificadoModelo;
import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.CertificadoModeloDto;

@Component
public class CertificadoModeloConverter {

  @Autowired
  private ModelMapper modelMapper;

  public CertificadoModeloDto convertToDto( final CertificadoModelo user ) {

    return Objects.isNull(user) ? null : modelMapper.map( user, CertificadoModeloDto.class );

  } 

  public CertificadoModelo convertToEntity( final CertificadoModeloDto dto ) {

    return Objects.isNull(dto) ? null : modelMapper.map( dto, CertificadoModelo.class );
    
  }

}