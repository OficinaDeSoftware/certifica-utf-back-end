package com.OficinaDeSoftware.EmissorCertificadosBackend.controller;

import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.ModelDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/model")
public class ModelController {

    @GetMapping
    public ResponseEntity<List<ModelDto>> listAllModels() {
        List<ModelDto> models = new ArrayList<>();

        return ResponseEntity.ok(models);
    }
}
