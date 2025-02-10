package com.OficinaDeSoftware.EmissorCertificadosBackend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.OficinaDeSoftware.EmissorCertificadosBackend.domain.User;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
     User findByEmail( String email );
     List<User> findByNrUuidIn( List<String> nrUuids );
}
