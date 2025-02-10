package com.OficinaDeSoftware.EmissorCertificadosBackend.repository;

import com.OficinaDeSoftware.EmissorCertificadosBackend.domain.Model;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ModelRepository extends MongoRepository<Model, String> {
}
