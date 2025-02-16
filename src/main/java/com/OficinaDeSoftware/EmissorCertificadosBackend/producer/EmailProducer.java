package com.OficinaDeSoftware.EmissorCertificadosBackend.producer;

import com.OficinaDeSoftware.EmissorCertificadosBackend.domain.Event;
import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.producer.EmailPayloadDto;
import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.producer.EmailTemplateContextDto;
import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.producer.EmailTemplateDto;
import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.response.UserResponseDto;
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

    EmailPayloadDto toConclusion( UserResponseDto userDto, final String certificateUrl, Event event ) {

        log.info( "Enviando email de conclusão do evento" );

        // TODO pensar em alguma maneira de tirar esses codigos fixos ( application.properties ? tabela no banco ? )

        List<EmailTemplateContextDto> contexts = Arrays.asList(
                new EmailTemplateContextDto("logoUrl", "https://firebasestorage.googleapis.com/v0/b/certifica-utf.appspot.com/o/logo.png?alt=media"),
                new EmailTemplateContextDto("receiverName", userDto.getName() ),
                new EmailTemplateContextDto("certificadoUrl", certificateUrl ),
                new EmailTemplateContextDto("aplicationName", "CertificaUTF" ),
                new EmailTemplateContextDto("nameEvent", event.getName() )
        );

        EmailTemplateDto emailTemplateDTO = new EmailTemplateDto(
                "conclusion",
                contexts
        );

        EmailPayloadDto emailPayloadDto = new EmailPayloadDto(
                userDto.getNrUuid(),
                "Parabéns! Evento concluido.",
                userDto.getEmail(),
                emailTemplateDTO
        );

        log.info( "Enviando o payload de conclusão do evento");

        return emailPayloadDto;

    }

    public void conclusion(UserResponseDto userDto, final String certificateUrl, Event event) {
        send( toConclusion( userDto, certificateUrl, event ) );
    }

    void send( EmailPayloadDto emailPayloadDTO ) {
        rabbitTemplate.convertAndSend( "", routingKey, emailPayloadDTO );
    }

}
