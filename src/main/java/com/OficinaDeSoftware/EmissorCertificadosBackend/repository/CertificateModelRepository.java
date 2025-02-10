package com.OficinaDeSoftware.EmissorCertificadosBackend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.OficinaDeSoftware.EmissorCertificadosBackend.domain.CertificateModel;

public interface CertificateModelRepository extends MongoRepository<CertificateModel, String> {}
