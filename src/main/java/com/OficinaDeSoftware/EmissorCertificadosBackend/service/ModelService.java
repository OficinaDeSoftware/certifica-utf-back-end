package com.OficinaDeSoftware.EmissorCertificadosBackend.service;

import com.OficinaDeSoftware.EmissorCertificadosBackend.converter.ModelConverter;
import com.OficinaDeSoftware.EmissorCertificadosBackend.domain.CertificateResposible;
import com.OficinaDeSoftware.EmissorCertificadosBackend.domain.Event;
import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.ModelDto;
import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.response.UserResponseDto;
import com.OficinaDeSoftware.EmissorCertificadosBackend.repository.ModelRepository;
import com.itextpdf.kernel.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.StringTemplateResolver;
import com.itextpdf.html2pdf.HtmlConverter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class ModelService {

    private final ModelRepository modelRepository;
    private final TemplateEngine templateEngine;
    private final ModelConverter modelConverter;

    public ModelService(ModelRepository modelRepository, ModelConverter modelConverter, TemplateEngine templateEngine ) {
        this.modelRepository = modelRepository;
        this.modelConverter = modelConverter;

        StringTemplateResolver stringTemplateResolver = new StringTemplateResolver();
        templateEngine.setTemplateResolver(stringTemplateResolver);
        this.templateEngine = templateEngine;
    }

    public List<ModelDto> findAll() {
        return modelRepository.findAll().stream().map( modelConverter::convertToDto ).toList();
    }

    public ModelDto findById( String id ) {
        return modelConverter.convertToDto( modelRepository.findById( id ).orElseThrow( () -> new RuntimeException( "Model not found" ) ) );
    }

    public String replaceTags( ModelDto model, Map<String, String> tagsParticipant, Context contextEvent ) throws IOException {

        final String rawModel = model.getHtml();

        tagsParticipant.forEach(contextEvent::setVariable);

        return templateEngine.process( rawModel, contextEvent );

    }

    public Map<String, String> toTagsFromParticipant( UserResponseDto userResponseDto ) {
        return Map.ofEntries(
            Map.entry( "receiver", userResponseDto.getName() )
        );
    }

    public Context toTagsFromEvent(final Event event ) {

        final String date = String.format( "De %s a %s", event.getStartDate().toString(), event.getEndDate().toString() );

        final CertificateResposible responsible = event
                .getCertificate()
                .getResponsible()
                .stream()
                .findFirst()
                .orElseThrow( () -> new RuntimeException( "Responsavel pela emissão não encontrado!" ) );

        Context context = new Context();
        context.setVariable("title", event.getName() );
        context.setVariable("workload", event.getWorkload().toString() );
        context.setVariable("date", date );
        context.setVariable("occupation", responsible.getOccupation() );
        context.setVariable("signature", responsible.getSignature() );
        context.setVariable("logoUrl", event.getCertificate().getIssuerLogoUrl() );
        context.setVariable( "description", event.getCertificate().getComplement() );

        return context;
    }

}
