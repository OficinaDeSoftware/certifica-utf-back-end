package com.OficinaDeSoftware.EmissorCertificadosBackend.repository;

import java.util.List;

import com.OficinaDeSoftware.EmissorCertificadosBackend.domain.EventParticipantCountProjection;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.OficinaDeSoftware.EmissorCertificadosBackend.domain.EventParticipant;

public interface EventParticipantRepository extends MongoRepository<EventParticipant, String> {

     List<EventParticipant> findAllByNrUuidParticipant( String nrUuidParticipant );

     boolean existsByNrUuidParticipantAndIdEvent( String nrUuidParticipant, String idEvent );

     List<EventParticipant> findAllByIdEvent(String idEvent );

     @Aggregation(pipeline = {
             "{ $group: { _id: '$idEvent', uniqueUsers: { $addToSet: '$nrUuidParticipant' } } }",
             "{ $project: { _id: 1, count: { $size: '$uniqueUsers' } } }"
     })
     List<EventParticipantCountProjection> countByIdEvent();

     void deleteByIdEventAndNrUuidParticipant( String idEvent, String nrUuidParticipant );
}
