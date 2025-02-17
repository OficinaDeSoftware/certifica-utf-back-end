package com.OficinaDeSoftware.EmissorCertificadosBackend.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import com.OficinaDeSoftware.EmissorCertificadosBackend.domain.EventParticipant;
import com.OficinaDeSoftware.EmissorCertificadosBackend.domain.EventParticipantCountProjection;
import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.response.EventBasicResponseDto;
import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.request.EventRequestDto;
import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.response.UserResponseDto;
import com.OficinaDeSoftware.EmissorCertificadosBackend.model.EventStatusEnum;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.OficinaDeSoftware.EmissorCertificadosBackend.converter.EventoConverter;
import com.OficinaDeSoftware.EmissorCertificadosBackend.domain.Event;
import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.response.EventResponseDto;
import com.OficinaDeSoftware.EmissorCertificadosBackend.repository.EventRepository;
import com.OficinaDeSoftware.EmissorCertificadosBackend.service.exception.ObjectNotFoundException;

@Slf4j
@Service
public class EventService {
    
    private final EventRepository repository;
    private final EventoConverter converter;
    private final EventParticipantService eventParticipantService;
    private final CertificateService certificateService;

    public EventService(
            EventParticipantService eventParticipantService,
            EventRepository repository, EventoConverter converter,
            CertificateService certificateService
    ) {
        this.repository = repository;
        this.converter = converter;
        this.eventParticipantService = eventParticipantService;
        this.certificateService = certificateService;
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

        final String nrUuidResponsible = params.getOrDefault( "responsible", null  );

        if( nrUuidResponsible != null ) {
            return findAllByNrUuidResponsible( nrUuidResponsible );
        }

        List<EventParticipantCountProjection> participantsByEvent = eventParticipantService.countByIdEvent();

        Map<String, Integer> participantsByEventMap = participantsByEvent.stream()
                .collect(Collectors.toMap(EventParticipantCountProjection::id, EventParticipantCountProjection::count ));

        List<EventBasicResponseDto> events = repository
        .findAll()
        .stream()
        .map( converter::toBasicResponseDto ).toList();

        events.forEach( current -> {
            current.setParticipantsCount( participantsByEventMap.getOrDefault(current.getIdEvent(), 0));
        });

        return events;
    }

    public List<EventBasicResponseDto> findAllByNrUuidResponsible( final String nrUuidResponsible ) {
        return repository.findAllByNrUuidResponsible( nrUuidResponsible )
        .stream()
        .map( converter::toBasicResponseDto )
        .collect( Collectors.toList() );
    }

    public EventResponseDto findById( final String idEvent ) {

        Event event = repository.findById( idEvent ).orElseThrow(() -> new ObjectNotFoundException("Evento não encontrado"));

        EventResponseDto eventDto = converter.convertToDto( event );

        eventDto.setParticipantsCount( eventParticipantService.findAllByIdEvent( idEvent ).size() );

        return eventDto;

    }

    public EventResponseDto insert( final EventRequestDto eventRequest ) {

        Event event = converter.convertToEntity( eventRequest );

        AtomicInteger index = new AtomicInteger(1);

        event.getDates().forEach( data -> data.setId( index.getAndIncrement() ) );

        Event newEvent = repository.insert( event );

        return converter.convertToDto(newEvent);
    }

    public EventResponseDto update( final String idEvent, final EventRequestDto eventRequest ) {
        Event event = converter.convertToEntity( eventRequest );
        event.setIdEvent( idEvent );
        return converter.convertToDto( repository.save( event ) );
    }

    public void finished( final String idEvent ) {

        final Event event = repository.findById( idEvent ).orElseThrow( () -> new ObjectNotFoundException("Evento não encontrado") );

        if( event.getStatus().equals( EventStatusEnum.FINISHED ) ) {
            throw new ObjectNotFoundException( "Evento ja finalizado!" );
        }

        event.setStatus( EventStatusEnum.FINISHED );

        List<UserResponseDto> participants = eventParticipantService.findAllByIdEvent(idEvent);

        certificateService.createCertificateByParticipants( participants, event );

        repository.save( event );

    }

    public void delete( final String idEvent ) {
        repository.deleteById( idEvent );
    }
}
