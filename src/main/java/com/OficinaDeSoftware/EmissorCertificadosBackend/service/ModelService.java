package com.OficinaDeSoftware.EmissorCertificadosBackend.service;

import com.OficinaDeSoftware.EmissorCertificadosBackend.converter.ModelConverter;
import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.ModelDto;
import com.OficinaDeSoftware.EmissorCertificadosBackend.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelService {

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private ModelConverter modelConverter;

    public List<ModelDto> findAll() {
        return modelRepository.findAll().stream().map(model -> modelConverter.convertToDto(model)).toList();
    }
}
