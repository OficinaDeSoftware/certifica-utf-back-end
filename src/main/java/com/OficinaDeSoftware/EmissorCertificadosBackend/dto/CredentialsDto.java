package com.OficinaDeSoftware.EmissorCertificadosBackend.dto;

import com.OficinaDeSoftware.EmissorCertificadosBackend.model.ProviderEnum;

// TODO take care of the exception IllegalArgumentException of ProviderEnum
public record CredentialsDto(
        ProviderEnum typeProvider,
        String idToken,
        String login,
        String password ) {
}
