package com.OficinaDeSoftware.EmissorCertificadosBackend.utils;

import com.itextpdf.html2pdf.HtmlConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Slf4j
public class FileHelper {

    public File htmlToPdf( final String html, final String identifier ) throws IOException {

        File pdfFile = File.createTempFile( identifier, ".pdf");

        try ( FileOutputStream fos = new FileOutputStream(pdfFile)) {

            HtmlConverter.convertToPdf(
                    html,
                    fos
            );

        } catch ( IOException e ) {

            log.error( e.getMessage() );

            return null;

        }

        return pdfFile;
    }

    public MultipartFile toMultiPartFile( final File file ) {
        return new MultiPartFileImplement( file );
    }

}
