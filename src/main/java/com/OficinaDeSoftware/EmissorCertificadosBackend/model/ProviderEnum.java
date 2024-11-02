package com.OficinaDeSoftware.EmissorCertificadosBackend.model;

public enum ProviderEnum {
    GOOGLE,
    UTFPR;

    public static ProviderEnum parse( String name ) {
        return valueOf( name.toUpperCase() );
    }
}