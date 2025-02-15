package com.OficinaDeSoftware.EmissorCertificadosBackend.controller;

import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.ModelDto;
import com.OficinaDeSoftware.EmissorCertificadosBackend.service.ModelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/certificate")
public class CertificateModelController {

    private final ModelService modelService;

    public CertificateModelController( ModelService modelService ) {
        this.modelService = modelService;
    }

    @GetMapping("/model")
    public ResponseEntity<List<ModelDto>> listAllModels() {
        return ResponseEntity.ok( modelService.findAll() );
    }
}
