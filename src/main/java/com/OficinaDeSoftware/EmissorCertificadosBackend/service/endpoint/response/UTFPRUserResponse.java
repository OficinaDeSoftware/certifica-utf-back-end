package com.OficinaDeSoftware.EmissorCertificadosBackend.service.endpoint.response;

public record UTFPRUserResponse (
        String name,
        String login,
        String email,
        String phone,
        String institution,
        String research,
        String area,
        String lattes,
        String studentCode,
        Integer registerSemester,
        Integer registerYear,
        String photo,
        Boolean external,
        Boolean active
) {
}
