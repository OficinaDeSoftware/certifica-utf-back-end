package com.OficinaDeSoftware.EmissorCertificadosBackend.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.OficinaDeSoftware.EmissorCertificadosBackend.domain.EventParticipant;
import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.response.EventBasicResponseDto;
import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.request.EventRequestDto;
import com.OficinaDeSoftware.EmissorCertificadosBackend.service.uploader.firebase.UploaderFirebaseService;
import org.springframework.stereotype.Service;

import com.OficinaDeSoftware.EmissorCertificadosBackend.converter.EventoConverter;
import com.OficinaDeSoftware.EmissorCertificadosBackend.domain.Event;
import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.response.EventResponseDto;
import com.OficinaDeSoftware.EmissorCertificadosBackend.repository.CertificateRepository;
import com.OficinaDeSoftware.EmissorCertificadosBackend.repository.DateEventRepository;
import com.OficinaDeSoftware.EmissorCertificadosBackend.repository.EventRepository;
import com.OficinaDeSoftware.EmissorCertificadosBackend.service.exception.ObjectNotFoundException;

@Service
public class EventService {
    
    private final EventRepository repository;
    private final EventoConverter converter;
    private final UserService userService;
    private final UploaderFirebaseService uploaderFirebaseService;
    private final EventParticipantService eventParticipantService;

    public EventService(
            EventParticipantService eventParticipantService,
            DateEventRepository dateEventRepository,
            CertificateRepository certificateRepository,
            EventRepository repository, EventoConverter converter,
            UserService userService,
            UploaderFirebaseService uploaderFirebaseService
    ) {
        this.repository = repository;
        this.converter = converter;
        this.userService = userService;
        this.uploaderFirebaseService = uploaderFirebaseService;
        this.eventParticipantService = eventParticipantService;
    }

    private List<EventBasicResponseDto> findAllByParticipant( final String nrUuidParticipant ) {

        final List<String> idEvents = eventParticipantService
                .findAllByNrUuidParticipant( nrUuidParticipant )
                .stream()
                .map( EventParticipant::getIdEvent )
                .toList();

        return repository.findAllByIdEventIn( idEvents )
                .stream()
                .map( converter::toBasicResponseDto )
                .toList();

    }

    public List<EventBasicResponseDto> findAll( Map<String, String> params ) {

        final String nrUuidParticipant = params.getOrDefault( "participant", null  );

        if( nrUuidParticipant != null ) {
            return findAllByParticipant( nrUuidParticipant );
        }

        return
        repository.findAll()
        .stream()
        .map( converter::toBasicResponseDto )
        .collect( Collectors.toList() );
    }

    public List<EventResponseDto> findAllByNrUuidResponsible(final String nrUuidResponsible ) {
        return repository.findAllByNrUuidResponsible( nrUuidResponsible )
        .stream()
        .map( converter::convertToDto )
        .collect( Collectors.toList() );
    }

    public EventResponseDto findById(final String idEvent ) {

        final Event event = repository.findById( idEvent ).orElseThrow(() -> new ObjectNotFoundException("Evento não encontrado"));

        return converter.convertToDto( event );

    }

    public EventResponseDto insert(final EventRequestDto evento ) {

//        final String dsBackgroundImageUrl = uploaderFirebaseService.image( evento.getBackgroundImage() );
//
        Event event = converter.convertToEntity(evento);
//
//        event.setDsBackgroundImageUrl( dsBackgroundImageUrl );
        
        Event newEvent = repository.insert( event );

        return converter.convertToDto(newEvent);
    }

    public EventResponseDto update(final String idEvent, final EventRequestDto eventoRequest ) {
        Event event = converter.convertToEntity( eventoRequest );
        event.setIdEvent( idEvent );
        return converter.convertToDto( repository.save( event ) );
    }

    public void delete( final String idEvent ) {
        repository.deleteById( idEvent );
    }
}
