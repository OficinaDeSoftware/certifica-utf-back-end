package com.OficinaDeSoftware.EmissorCertificadosBackend.service.auth.Provider;

import com.OficinaDeSoftware.EmissorCertificadosBackend.model.ProviderEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ProviderTokenServiceFactory {

    private final ProviderTokenService googleProviderTokenService;
    private final ProviderTokenService utfprProviderTokenService;

    @Autowired
    public ProviderTokenServiceFactory(
            @Qualifier("googleProviderTokenService") ProviderTokenService googleProviderTokenService,
            @Qualifier("utfprProviderTokenService") ProviderTokenService utfprProviderTokenService
    ) {
        this.googleProviderTokenService = googleProviderTokenService;
        this.utfprProviderTokenService = utfprProviderTokenService;
    }

    public ProviderTokenService getService( ProviderEnum providerType ) {
        return switch (providerType) {
            case GOOGLE -> googleProviderTokenService;
            case UTFPR -> utfprProviderTokenService;
            default -> null;
        };
    }
}