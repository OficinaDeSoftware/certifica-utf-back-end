package com.OficinaDeSoftware.EmissorCertificadosBackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OficinaDeSoftware.EmissorCertificadosBackend.converter.UserConverter;
import com.OficinaDeSoftware.EmissorCertificadosBackend.domain.User;
import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.UserDto;
import com.OficinaDeSoftware.EmissorCertificadosBackend.repository.UserRepository;
import com.OficinaDeSoftware.EmissorCertificadosBackend.service.exception.ObjectNotFoundException;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    private final UserConverter userConverter;

    public UserService( UserRepository repository, UserConverter userConverter) {
        this.repository = repository;
        this.userConverter = userConverter;
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
    
}