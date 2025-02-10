package com.OficinaDeSoftware.EmissorCertificadosBackend.service.uploader.firebase;

import com.OficinaDeSoftware.EmissorCertificadosBackend.api.endpoint.UploaderFirebaseEndpoint;
import com.OficinaDeSoftware.EmissorCertificadosBackend.api.response.UploaderImageResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploaderFirebaseService {

    private final UploaderFirebaseEndpoint uploaderFirebaseEndpoint;

    public UploaderFirebaseService( UploaderFirebaseEndpoint uploaderFirebaseEndpoint ) {
        this.uploaderFirebaseEndpoint = uploaderFirebaseEndpoint;
    }

    public String image( final MultipartFile file ) {

        if( file == null ) {
            return null;
        }

       final UploaderImageResponse response = uploaderFirebaseEndpoint.image( file ).getBody();

       if( response == null ) {
           return null;
       }

       return response.url();
    }
}
