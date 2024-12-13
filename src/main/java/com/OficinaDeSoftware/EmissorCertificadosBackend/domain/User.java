package com.OficinaDeSoftware.EmissorCertificadosBackend.domain;

import java.util.List;

import com.OficinaDeSoftware.EmissorCertificadosBackend.model.RoleEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user")
public class User {

    @Id
    private String nrUuid;
    @NotBlank
    private String name;
    @Email
    private String email;
    private String urlImagemPerfil;
    private Integer tipousuario;
    @Enumerated(EnumType.STRING)
    private List<RoleEnum> roles;
    
}