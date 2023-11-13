package com.OficinaDeSoftware.EmissorCertificadosBackend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.OficinaDeSoftware.EmissorCertificadosBackend.domain.User;

public interface UserRepository extends MongoRepository<User, String> {}
