package com.OficinaDeSoftware.EmissorCertificadosBackend.model;

import java.util.List;
import java.util.stream.Collectors;

// TODO as roles por padrão tem um prefixo ROLE_ que é definido no AuthorityAuthorizationManager hasRole, avaliar alguma forma de tirar isso
public enum RoleEnum {
    ROLE_USER,
    ROLE_ADMIN;

    public static List<String> toString( List<RoleEnum> roles ) {
        return roles.stream().map( current -> current.name() ).collect( Collectors.toList() );
    }
}