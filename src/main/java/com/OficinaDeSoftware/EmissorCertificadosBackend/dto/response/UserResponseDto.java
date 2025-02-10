package com.OficinaDeSoftware.EmissorCertificadosBackend.dto.response;

import com.OficinaDeSoftware.EmissorCertificadosBackend.model.RoleEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

        private String nrUuid;

        private String name;

        private String email;

        @JsonProperty("profileUrl")

        private String urlImagemPerfil;

        private List<RoleEnum> roles;

}
