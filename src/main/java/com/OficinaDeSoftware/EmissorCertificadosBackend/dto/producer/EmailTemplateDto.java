package com.OficinaDeSoftware.EmissorCertificadosBackend.dto.producer;

import java.util.List;

public record EmailTemplateDto(
        String name,
        List<EmailTemplateContextDto> contexts
) {}
