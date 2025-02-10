package com.OficinaDeSoftware.EmissorCertificadosBackend.converter;

import java.util.Objects;

import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.response.UserResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.OficinaDeSoftware.EmissorCertificadosBackend.domain.User;
import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.UserDto;

@Component
public class UserConverter {

  private final ModelMapper modelMapper;

  public UserConverter( ModelMapper modelMapper ) {
    this.modelMapper = modelMapper;
  }

  public UserDto convertToDto( final User user ) {

    return Objects.isNull(user) ? null : modelMapper.map( user, UserDto.class );

  } 

  public User convertToEntity( final UserDto dto ) {

    return Objects.isNull(dto) ? null : modelMapper.map( dto, User.class );
    
  }

  public UserResponseDto toResponseDto( User user ) {
    return Objects.isNull(user) ? null : modelMapper.map( user, UserResponseDto.class );
  }

}