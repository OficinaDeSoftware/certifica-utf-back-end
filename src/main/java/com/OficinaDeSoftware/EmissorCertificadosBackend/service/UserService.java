package com.OficinaDeSoftware.EmissorCertificadosBackend.service;

import com.OficinaDeSoftware.EmissorCertificadosBackend.converter.UserResponseConverter;
import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.response.UserResponseDto;
import org.springframework.stereotype.Service;

import com.OficinaDeSoftware.EmissorCertificadosBackend.converter.UserConverter;
import com.OficinaDeSoftware.EmissorCertificadosBackend.domain.User;
import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.UserDto;
import com.OficinaDeSoftware.EmissorCertificadosBackend.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    private final UserConverter userConverter;

    private final UserResponseConverter userResponseConverter;

    public UserService( UserRepository repository, UserConverter userConverter, UserResponseConverter userResponseConverter) {
        this.repository = repository;
        this.userConverter = userConverter;
        this.userResponseConverter = userResponseConverter;
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public void save(final UserDto userDto) {
        repository.save( userConverter.convertToEntity( userDto ) );
    }

    public User findByNrUuid( final String nrUuid ) {
        return repository.findById(nrUuid).orElse(null);
    }

    public UserResponseDto getByNrUuid(final String nrUuid ) {
        return repository.findById(nrUuid)
                .map(userResponseConverter::convertToDto)
                .orElse(null);
    }
    
}