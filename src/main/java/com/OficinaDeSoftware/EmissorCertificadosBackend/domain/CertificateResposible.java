package com.OficinaDeSoftware.EmissorCertificadosBackend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CertificateResposible {

    private String signature;

    private String occupation;
}
