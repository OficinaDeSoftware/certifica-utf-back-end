package com.OficinaDeSoftware.EmissorCertificadosBackend.producer;

import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.UserDto;
import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.producer.EmailPayloadDto;
import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.producer.EmailTemplateContextDto;
import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.producer.EmailTemplateDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class EmailProducer {

    final RabbitTemplate rabbitTemplate;

    public EmailProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value(value = "${broker.queue.email.name}")
    private String routingKey;

    EmailPayloadDto toRegister( UserDto userDto ) {

        log.info( "Enviando email de novo usuario" );

        // TODO pensar em alguma maneira de tirar esses codigos fixos ( application.properties ? tabela no banco ? )

        List<EmailTemplateContextDto> contexts = Arrays.asList(
            new EmailTemplateContextDto("logoUrl", "https://firebasestorage.googleapis.com/v0/b/certifica-utf.appspot.com/o/logo.png?alt=media&token=a1acd6f8-b14d-4416-95ba-1db4be14068d"),
            new EmailTemplateContextDto("receiverName", userDto.getName() ),
            new EmailTemplateContextDto("loginUrl", "https://certificautf.vercel.app" ),
            new EmailTemplateContextDto("aplicationName", "CertificaUTF" )
        );

        EmailTemplateDto emailTemplateDTO = new EmailTemplateDto(
            "register",
            contexts
        );

        EmailPayloadDto emailPayloadDto = new EmailPayloadDto(
            userDto.getNrUuid(),
        "Cadastro completo no CertificaUTF!",
            userDto.getEmail(),
            emailTemplateDTO
        );

        log.info( "Enviando o payload de novo registro {}", emailPayloadDto.toString() );

        return emailPayloadDto;

    }

    public void register( UserDto userDto ) {
        send( toRegister( userDto ) );
    }

    void send( EmailPayloadDto emailPayloadDTO ) {
        rabbitTemplate.convertAndSend( "", routingKey, emailPayloadDTO );
    }

}
