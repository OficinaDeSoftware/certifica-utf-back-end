package com.OficinaDeSoftware.EmissorCertificadosBackend.converter;

import com.OficinaDeSoftware.EmissorCertificadosBackend.domain.User;
import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.response.UserResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserResponseConverter {

    @Autowired
    private ModelMapper modelMapper;

    public UserResponseDto convertToDto(final User user ) {

        return Objects.isNull(user) ? null : modelMapper.map( user, UserResponseDto.class );

    }

    public User convertToEntity( final UserResponseDto dto ) {

        return Objects.isNull(dto) ? null : modelMapper.map( dto, User.class );

    }

}
