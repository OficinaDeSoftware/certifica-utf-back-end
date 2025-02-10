package com.OficinaDeSoftware.EmissorCertificadosBackend.dto.Response;

import com.OficinaDeSoftware.EmissorCertificadosBackend.model.RoleEnum;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record UserResponseDto(
        String nrUuid,
        String name,
        String email,
        @JsonProperty("profileUrl")
        String urlImagemPerfil,
        List<RoleEnum> roles) {
}
