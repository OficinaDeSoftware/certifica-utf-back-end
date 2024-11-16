package com.OficinaDeSoftware.EmissorCertificadosBackend.service.auth;

import java.util.Arrays;
import java.util.List;

import com.OficinaDeSoftware.EmissorCertificadosBackend.producer.EmailProducer;
import com.OficinaDeSoftware.EmissorCertificadosBackend.service.auth.Provider.ProviderTokenServiceFactory;
import com.OficinaDeSoftware.EmissorCertificadosBackend.service.exception.UnknowProviderTokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OficinaDeSoftware.EmissorCertificadosBackend.converter.UserConverter;
import com.OficinaDeSoftware.EmissorCertificadosBackend.domain.User;
import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.CredentialsDto;
import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.UserDto;
import com.OficinaDeSoftware.EmissorCertificadosBackend.model.ProviderModel;
import com.OficinaDeSoftware.EmissorCertificadosBackend.model.RoleEnum;
import com.OficinaDeSoftware.EmissorCertificadosBackend.service.UserService;
import com.OficinaDeSoftware.EmissorCertificadosBackend.service.auth.Provider.ProviderTokenService;

@Service
public class AuthenticationService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserConverter userConverter;

    private final ProviderTokenServiceFactory providerTokenServiceFactory;
    private final EmailProducer emailProducer;

    public AuthenticationService(ProviderTokenServiceFactory providerTokenServiceFactory, EmailProducer emailProducer){
        this.providerTokenServiceFactory = providerTokenServiceFactory;
        this.emailProducer = emailProducer;
    }
    
    public UserDto authenticate( CredentialsDto credentialsDto ) throws RuntimeException {

        ProviderTokenService providerTokenService = providerTokenServiceFactory.getService( credentialsDto.typeProvider() );

        if( providerTokenService == null ) {
            throw new UnknowProviderTokenException();
        }

        final ProviderModel provider = providerTokenService.process( credentialsDto );

        final User registeredUser = userService.findByNrUuid( provider.getNrUuid() );

        if( registeredUser != null ){
            return userConverter.convertToDto( registeredUser );
        }

        return registerUser( provider );
    }

    public UserDto registerUser( final ProviderModel provider ) {

        UserDto userDto = userConverter.convertToDto( provider );
        userDto.setRoles( List.of( RoleEnum.ROLE_USER ) );
        userService.save( userDto );

        emailProducer.register( userDto );

        return userDto;
    }
}
