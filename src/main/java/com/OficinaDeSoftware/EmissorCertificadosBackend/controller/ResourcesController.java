package com.OficinaDeSoftware.EmissorCertificadosBackend.controller;

import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.UploadDto;
import com.OficinaDeSoftware.EmissorCertificadosBackend.service.ResourcesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/resources")
public class ResourcesController {

    final private ResourcesService resourcesService;

    public ResourcesController( ResourcesService resourcesService ) {
        this.resourcesService = resourcesService;
    }

    @PostMapping("/upload")
    ResponseEntity<UploadDto> upload(  @RequestParam("file") MultipartFile multipartFile, @RequestParam String identifier ) {
        return ResponseEntity.ok().body( resourcesService.upload( multipartFile, identifier ) );
    }

}
