package com.OficinaDeSoftware.EmissorCertificadosBackend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.OficinaDeSoftware.EmissorCertificadosBackend.domain.EventCheckin;

public interface EventCheckinRepository extends MongoRepository<EventCheckin, String> {

    EventCheckin findByIdEventAndNrUuidParticipant( String idEvent, String nrUuidParticipant );

}
