package com.OficinaDeSoftware.EmissorCertificadosBackend.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OficinaDeSoftware.EmissorCertificadosBackend.converter.CertificadoModeloConverter;
import com.OficinaDeSoftware.EmissorCertificadosBackend.domain.CertificateModel;
import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.CertificadoModeloDto;
import com.OficinaDeSoftware.EmissorCertificadosBackend.repository.CertificateModelRepository;
import com.OficinaDeSoftware.EmissorCertificadosBackend.service.exception.ObjectNotFoundException;

@Service
public class CertificateModelService {
    
    @Autowired
    private CertificateModelRepository repository;

    @Autowired
    private CertificadoModeloConverter converter;

    public List<CertificateModel> findAll() {
        return repository.findAll();
    }

    public CertificateModel findById(String codigo) {
        return repository.findById(codigo).orElseThrow(() -> new ObjectNotFoundException("CertificadoModelo não encontrado"));
    }

    public CertificateModel insert(CertificadoModeloDto certificadoModelo) {
        return repository.insert(converter.convertToEntity(certificadoModelo));
    }

    public CertificateModel update(CertificadoModeloDto certificadoModelo) {
        CertificateModel certificateModelAtualizado = findById(certificadoModelo.getIdCertificadoModelo());
        BeanUtils.copyProperties(certificadoModelo, certificateModelAtualizado);
        return repository.save(certificateModelAtualizado);
    }

    public void delete(String codigo) {
        findById(codigo);
        repository.deleteById(codigo);
    }
}
