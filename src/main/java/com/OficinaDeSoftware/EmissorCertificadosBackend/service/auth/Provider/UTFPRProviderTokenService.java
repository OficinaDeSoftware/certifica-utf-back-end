package com.OficinaDeSoftware.EmissorCertificadosBackend.service.auth.Provider;

import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.CredentialsDto;
import com.OficinaDeSoftware.EmissorCertificadosBackend.model.ProviderModel;
import com.OficinaDeSoftware.EmissorCertificadosBackend.service.endpoint.request.UTFPRAuthRequest;
import com.OficinaDeSoftware.EmissorCertificadosBackend.service.endpoint.response.UTFPRUserResponse;
import com.OficinaDeSoftware.EmissorCertificadosBackend.service.endpoint.utfpr.UTFPREndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service( "utfprProviderTokenService" )
public class UTFPRProviderTokenService implements ProviderTokenService {

    private final UTFPREndpoint utfprEndpoint;

    public UTFPRProviderTokenService( UTFPREndpoint utfprEndpoint ) {
        this.utfprEndpoint = utfprEndpoint;
    }

    public String auth( final UTFPRAuthRequest request ) {
        return utfprEndpoint.auth( request );
    }

    public UTFPRUserResponse userProfile( final String token ) {
        return utfprEndpoint.userProfile( token );
    }

    @Override
    public ProviderModel process( final CredentialsDto credentialsDto ) throws RuntimeException {

        log.info("Init check utfpr auth");

        final UTFPRAuthRequest request = new UTFPRAuthRequest( credentialsDto.login(), credentialsDto.password() );

        final String token = auth( request );

        final UTFPRUserResponse response = userProfile( token );

        final ProviderModel providerModel = ProviderModel.builder()
        .name( response.name() )
        .email( response.email() )
        .nrUuid( response.studentCode() )
        .build();

        log.info("end of utfpr auth [HAS_PROVIDER] {}", providerModel != null);

        return providerModel;

    }

}
