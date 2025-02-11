package com.OficinaDeSoftware.EmissorCertificadosBackend.service;

import com.OficinaDeSoftware.EmissorCertificadosBackend.domain.CertificateParticipant;
import com.OficinaDeSoftware.EmissorCertificadosBackend.domain.Event;
import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.ModelDto;
import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.response.UserResponseDto;
import com.OficinaDeSoftware.EmissorCertificadosBackend.producer.EmailProducer;
import com.OficinaDeSoftware.EmissorCertificadosBackend.service.uploader.firebase.UploaderFirebaseService;
import com.OficinaDeSoftware.EmissorCertificadosBackend.utils.FileHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class CertificateService {

    private final ModelService modelService;
    private final UploaderFirebaseService uploaderFirebaseService;
    private final CertificateParticipantService certificateParticipantService;
    private final EmailProducer emailProducer;

    public CertificateService (
            ModelService modelService,
            UploaderFirebaseService uploaderFirebaseService,
            CertificateParticipantService certificateParticipantService,
            EmailProducer emailProducer
    ) {
        this.modelService = modelService;
        this.uploaderFirebaseService = uploaderFirebaseService;
        this.certificateParticipantService = certificateParticipantService;
        this.emailProducer = emailProducer;
    }

    // TODO parar de usar o UserResponseDto, usar o domain
    public void createCertificateByParticipants(List<UserResponseDto> participants, Event event ) {

        ModelDto certificateModel = modelService.findById( event.getCertificate().getModelId() );

        Context contextEvent = modelService.toTagsFromEvent( event );

        participants.forEach( participant -> {

            String nameCertificate = String.format("%s-%s", participant.getName().trim(), event.getName().trim() );

            Map<String, String> tagsParticipant =  modelService.toTagsFromParticipant( participant );

            try {

                final String rawHtmlCertificate = modelService.replaceTags( certificateModel, tagsParticipant, contextEvent );

                FileHelper fileHelper = new FileHelper();

                File file = fileHelper.htmlToPdf( rawHtmlCertificate, nameCertificate );

                MultipartFile multiPartFile = fileHelper.toMultiPartFile( file );

                // TODO Seria bom rodar em thread ou outro servico, mas como eh apenas o MVP de boa
                // TODO precisa tratar casos de erro
                final String certifiedURL = uploaderFirebaseService.image( multiPartFile, nameCertificate );

                emailProducer.conclusion( participant, certifiedURL, event );

                CertificateParticipant certificateParticipant = CertificateParticipant.builder()
                        .certificateUrl( certifiedURL )
                        .nrUuidParticipant( participant.getNrUuid() )
                        .idEvent( event.getIdEvent() )
                        .build();

                certificateParticipantService.insert( certificateParticipant );

            } catch( IOException exception ) {
                log.error( "Failed to upload certificate", exception );
            }

        });

    }

}
