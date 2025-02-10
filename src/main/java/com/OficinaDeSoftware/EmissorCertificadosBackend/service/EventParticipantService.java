package com.OficinaDeSoftware.EmissorCertificadosBackend.service;

import java.util.List;

import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.response.UserResponseDto;
import org.springframework.stereotype.Service;

import com.OficinaDeSoftware.EmissorCertificadosBackend.converter.EventoParticipanteConverter;
import com.OficinaDeSoftware.EmissorCertificadosBackend.converter.UserConverter;
import com.OficinaDeSoftware.EmissorCertificadosBackend.domain.EventParticipant;
import com.OficinaDeSoftware.EmissorCertificadosBackend.domain.User;
import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.EventParticipantDto;
import com.OficinaDeSoftware.EmissorCertificadosBackend.repository.EventParticipantRepository;
import com.OficinaDeSoftware.EmissorCertificadosBackend.repository.UserRepository;
import com.OficinaDeSoftware.EmissorCertificadosBackend.service.exception.ObjectNotFoundException;

@Service
public class EventParticipantService {
    
    private final EventParticipantRepository repository;

    private final EventoParticipanteConverter converter;

    final UserRepository userRepository;

    final UserConverter userConverter;

    public EventParticipantService(
            EventParticipantRepository repository,
            EventoParticipanteConverter converter,
            UserRepository userRepository,
            UserConverter userConverter
    ) {
        this.repository = repository;
        this.converter = converter;
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }


    public UserResponseDto insert( final String nrUuidParticipant, final EventParticipantDto participantDto ) {

        EventParticipant participant = converter.convertToEntity( participantDto );
        participant.setNrUuidParticipant( nrUuidParticipant );

        final User user = userRepository.findById( participant.getNrUuidParticipant() )
                .orElseThrow( () -> new ObjectNotFoundException("Usuario não encontrado") );

        if( repository.existsByNrUuidParticipantAndIdEvent( user.getNrUuid(), participantDto.getIdEvent() ) ){
            return userConverter.toResponseDto( user );
        }

        repository.insert( participant );

        return userConverter.toResponseDto( user );

    }

     public boolean existsByNrUuidParticipantAndIdEvent( String nrUuidParticipant, String idEvent ){
        return repository.existsByNrUuidParticipantAndIdEvent(nrUuidParticipant, idEvent);
     }

    public List<EventParticipant> findAllByNrUuidParticipant( String nrUuidParticipant ) {
        return repository.findAllByNrUuidParticipant( nrUuidParticipant );
    }

    public List<UserResponseDto> findAllByIdEvent(final String idEvent ) {

        final List<String> nrUuidParticipants = repository
                .findAllByIdEvent( idEvent )
                .stream()
                .map( EventParticipant::getNrUuidParticipant )
                .toList();

        return userRepository
                .findByNrUuidIn( nrUuidParticipants )
                .stream()
                .map( user -> new UserResponseDto (
                        user.getNrUuid(),
                        user.getName(),
                        user.getEmail(),
                        user.getUrlImagemPerfil(),
                        user.getRoles() )
                )
                .toList();

    };


    public void remove( final String nrUuidParticipant, final EventParticipantDto participantDto ) {
        repository.deleteByIdEventAndNrUuidParticipant( participantDto.getIdEvent(), nrUuidParticipant );
    }
}
