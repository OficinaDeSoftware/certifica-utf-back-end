package com.OficinaDeSoftware.EmissorCertificadosBackend.dto.producer;

public record EmailPayloadDto(
        String nrUuidReceiver,
        String subject,
        String receiver,
        EmailTemplateDto template
) {}
