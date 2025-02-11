package com.OficinaDeSoftware.EmissorCertificadosBackend.service;

import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.UploadDto;
import com.OficinaDeSoftware.EmissorCertificadosBackend.service.uploader.firebase.UploaderFirebaseService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ResourcesService {

    private final UploaderFirebaseService uploaderFirebaseService;

    public ResourcesService( UploaderFirebaseService uploaderFirebaseService ) {
        this.uploaderFirebaseService = uploaderFirebaseService;
    }

    public UploadDto upload(final MultipartFile file, final String identifier ) {
        return new UploadDto( uploaderFirebaseService.image( file, identifier ) );
    }

}
