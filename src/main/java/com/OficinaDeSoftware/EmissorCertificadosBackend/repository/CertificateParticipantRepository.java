package com.OficinaDeSoftware.EmissorCertificadosBackend.repository;

import com.OficinaDeSoftware.EmissorCertificadosBackend.domain.CertificateParticipant;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CertificateParticipantRepository extends MongoRepository<CertificateParticipant, String> {

    List<CertificateParticipant> findAllByNrUuidParticipant( String nrUuidParticipant );

}
