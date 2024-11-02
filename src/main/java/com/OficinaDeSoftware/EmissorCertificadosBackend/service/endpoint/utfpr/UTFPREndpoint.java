package com.OficinaDeSoftware.EmissorCertificadosBackend.service.endpoint.utfpr;

import com.OficinaDeSoftware.EmissorCertificadosBackend.service.endpoint.request.UTFPRAuthRequest;
import com.OficinaDeSoftware.EmissorCertificadosBackend.service.endpoint.response.UTFPRUserResponse;
import com.OficinaDeSoftware.EmissorCertificadosBackend.service.exception.BadCredentialsException;
import com.OficinaDeSoftware.EmissorCertificadosBackend.service.exception.ProviderAuthFailedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class UTFPREndpoint {

    private final WebClient webClient;

    public UTFPREndpoint( WebClient.Builder builder, @Value("${utfpr.baseurl}") String baseUrl ) {
        this.webClient = builder.baseUrl( baseUrl ).build();
    }

    public String auth( final UTFPRAuthRequest request ) {

        log.info("Init request for utfpr auth");

        final String token = webClient
        .post()
        .uri("/login")
        .contentType(MediaType.APPLICATION_JSON)
        .body( Mono.just( request ), UTFPRAuthRequest.class )
        .retrieve()
        .onStatus( HttpStatusCode::is4xxClientError, clientResponse ->
            Mono.error( new BadCredentialsException("Unauthorized access, invalid credentials"))
        )
        .onStatus( HttpStatusCode::is5xxServerError, clientResponse ->
            Mono.error( new ProviderAuthFailedException() )
        )
        .bodyToMono( String.class )
        .block();

        log.info("End of request [HAS_TOKEN] {}", token != null );

        return token;
    }

    public UTFPRUserResponse userProfile( final String token ) {

        log.info("Init request for utfpr user profile");

        final UTFPRUserResponse response = webClient
        .get()
        .uri("/user/profile")
        .header( HttpHeaders.AUTHORIZATION, String.format( "Bearer %s", token ) )
        .retrieve()
        .bodyToMono(UTFPRUserResponse.class)
        .onErrorResume( exception ->
            Mono.error( new ProviderAuthFailedException() )
        )
        .block();

        log.info("End of request [HAS_RESPONSE] {}", response != null );

        return response;

    }

}
