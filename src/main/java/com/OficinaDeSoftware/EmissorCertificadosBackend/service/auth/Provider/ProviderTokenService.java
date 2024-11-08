package com.OficinaDeSoftware.EmissorCertificadosBackend.service.auth.Provider;

import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.CredentialsDto;
import org.springframework.stereotype.Service;

import com.OficinaDeSoftware.EmissorCertificadosBackend.model.ProviderModel;

public interface ProviderTokenService {
    public ProviderModel process( final CredentialsDto credentialsDto ) throws RuntimeException;
}
