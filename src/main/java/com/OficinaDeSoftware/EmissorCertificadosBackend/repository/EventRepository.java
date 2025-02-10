package com.OficinaDeSoftware.EmissorCertificadosBackend.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.OficinaDeSoftware.EmissorCertificadosBackend.domain.Event;

public interface EventRepository extends MongoRepository<Event, String> {

    List<Event> findAllByNrUuidResponsible(String nrUuidResponsible );
    List<Event> findAllByIdEventIn(List<String> idEvents );

    // List<Evento> findAllPersonalizado();
}
