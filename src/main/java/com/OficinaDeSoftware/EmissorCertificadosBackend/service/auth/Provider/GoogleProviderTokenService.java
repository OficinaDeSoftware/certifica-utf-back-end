package com.OficinaDeSoftware.EmissorCertificadosBackend.service.auth.Provider;

import java.util.Collections;

import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.CredentialsDto;
import com.OficinaDeSoftware.EmissorCertificadosBackend.service.exception.BadCredentialsException;
import com.OficinaDeSoftware.EmissorCertificadosBackend.service.exception.ProviderAuthFailedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.OficinaDeSoftware.EmissorCertificadosBackend.model.ProviderModel;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;

import org.springframework.beans.factory.annotation.Value;

@Slf4j
@Service("googleProviderTokenService")
public class GoogleProviderTokenService implements ProviderTokenService {

    @Value("${spring.security.oauth2.client.registration.google.clientId}")
    private String clientID;

    @Override 
    public ProviderModel process( final CredentialsDto credentialsDto ) throws RuntimeException {

        log.error("Init check ID Token of GOOGLE");

        final GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
        .setAudience(Collections.singletonList( clientID ) )
        .build();

        GoogleIdToken idToken;

        try {
            idToken = verifier.verify( credentialsDto.idToken() );
        } catch ( Exception e ) {
            log.error("Fail on verify ID Token [WHAT] {}", e.getMessage());
            throw new ProviderAuthFailedException( "Fail on verify ID Token" );
        }

        if( idToken == null ){
            // TODO change exception
            log.info("Id token invalid");
            throw new ProviderAuthFailedException("Id Token invalid");
        }

        final Payload payload = idToken.getPayload();

        final ProviderModel providerModel = ProviderModel.builder()
        .name( payload.get("name").toString() )
        .nrUuid( payload.getSubject() )
        .email( payload.getEmail() )
        .build();

        log.info("End of google auth [HAS_PROVIDER] {}", providerModel != null);

        return providerModel;
    }

}
