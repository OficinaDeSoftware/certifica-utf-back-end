package com.OficinaDeSoftware.EmissorCertificadosBackend.converter;

import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.OficinaDeSoftware.EmissorCertificadosBackend.domain.CertificateModel;
import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.CertificadoModeloDto;

@Component
public class CertificadoModeloConverter {

  @Autowired
  private ModelMapper modelMapper;

  public CertificadoModeloDto convertToDto( final CertificateModel user ) {

    return Objects.isNull(user) ? null : modelMapper.map( user, CertificadoModeloDto.class );

  } 

  public CertificateModel convertToEntity(final CertificadoModeloDto dto ) {

    return Objects.isNull(dto) ? null : modelMapper.map( dto, CertificateModel.class );
    
  }

}