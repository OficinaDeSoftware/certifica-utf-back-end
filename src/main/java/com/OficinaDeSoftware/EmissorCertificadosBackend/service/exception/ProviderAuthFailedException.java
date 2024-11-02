package com.OficinaDeSoftware.EmissorCertificadosBackend.service.exception;

import org.springframework.security.core.AuthenticationException;

public class ProviderAuthFailedException extends AuthenticationException {

    public ProviderAuthFailedException() {
        super("Fail on provider authentication, contact administrator.");
    }

    public ProviderAuthFailedException(String msg) {
        super(msg);
    }

}
