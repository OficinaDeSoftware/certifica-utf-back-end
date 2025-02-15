package com.OficinaDeSoftware.EmissorCertificadosBackend.service;

import com.OficinaDeSoftware.EmissorCertificadosBackend.domain.DateEvent;
import com.OficinaDeSoftware.EmissorCertificadosBackend.domain.Event;
import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.EventCheckinDto;
import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.request.EventCheckinRequestDto;
import com.OficinaDeSoftware.EmissorCertificadosBackend.repository.EventRepository;
import com.OficinaDeSoftware.EmissorCertificadosBackend.service.exception.ObjectNotFoundException;
import com.OficinaDeSoftware.EmissorCertificadosBackend.utils.LocalDateHelper;
import com.OficinaDeSoftware.EmissorCertificadosBackend.utils.LocalDateTimeHelper;
import com.OficinaDeSoftware.EmissorCertificadosBackend.utils.LocalTimeHelper;
import org.springframework.stereotype.Service;

import com.OficinaDeSoftware.EmissorCertificadosBackend.converter.EventoCheckinConverter;
import com.OficinaDeSoftware.EmissorCertificadosBackend.domain.EventCheckin;
import com.OficinaDeSoftware.EmissorCertificadosBackend.repository.EventCheckinRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.function.Predicate;

@Service
public class EventCheckinService {
    
    private final EventCheckinRepository repository;
    private final EventoCheckinConverter converter;
    private final EventRepository eventRepository;
    private final EventParticipantService eventParticipantService;

    public EventCheckinService(
            EventoCheckinConverter converter,
            EventCheckinRepository repository,
            EventRepository eventRepository,
            EventParticipantService eventParticipantService
    ) {
        this.converter = converter;
        this.repository = repository;
        this.eventRepository = eventRepository;
        this.eventParticipantService = eventParticipantService;
    }

    public boolean hasValidCheckin( final EventCheckin eventCheckin ) {

        if( eventCheckin == null ){
            return false;
        }

        final LocalDateTime dhCheckin = eventCheckin.getDhCheckin();
        final LocalDateTime dhCheckout = eventCheckin.getDhCheckout();

        if ( dhCheckout != null && dhCheckin != null ) {
            return true;
        }

        // TODO criar umas exceptions para essa rotina

        if( dhCheckin == null && dhCheckout != null ){
            throw new ObjectNotFoundException("Checkin invalido! Data de checkout existente, mas sem data de entrada! Entre em contato com o administrador.");
        }

        if( dhCheckin != null && dhCheckin.isAfter( LocalDateTime.now() ) ){
            throw new ObjectNotFoundException("Checkin invalido! Data de checkin maior que a data atual! Entre em contato com o administrador.");
        }

        if( dhCheckin != null && dhCheckin.isBefore( LocalDateTime.now() ) ){
            return true;
        }

        return false;

    }

    public EventCheckinDto checkout(final String idEvent, EventCheckinRequestDto checkinRequestDto ) {

        final EventCheckin actualCheckin = repository.findByIdEventAndNrUuidParticipant( idEvent, checkinRequestDto.getNrUuidParticipant() );

        if( actualCheckin == null || actualCheckin.getDhCheckin() == null ){
            throw new ObjectNotFoundException( "Checkin inicial não  realizado, não  é possivel fazer o checkout!" );
        }

        if( actualCheckin.getDhCheckout() != null ){
            throw new ObjectNotFoundException( "Checkout ja realizado!" );
        }

        actualCheckin.setDhCheckout( LocalDateTimeHelper.now() );

        return converter.convertToDto( repository.save( actualCheckin ) );

    }

    public EventCheckinDto checkin( final String idEvent, EventCheckinRequestDto checkinRequestDto ) throws ObjectNotFoundException {

        final boolean hasRelation = eventParticipantService.existsByNrUuidParticipantAndIdEvent( checkinRequestDto.getNrUuidParticipant(), idEvent );

        final EventCheckin actualCheckin = repository.findByIdEventAndNrUuidParticipant( idEvent, checkinRequestDto.getNrUuidParticipant() );

        if( hasValidCheckin( actualCheckin ) ) {
            return converter.convertToDto( actualCheckin );
        }

        if( !hasRelation ) {
            throw new ObjectNotFoundException("Participante não cadastrado ao evento!");
        }

        Event event = eventRepository.findById( idEvent ).orElseThrow( () -> new ObjectNotFoundException("Evento não encontrado") );

        final LocalDate dhActual = LocalDateHelper.now();
        final LocalTime timeActual = LocalTimeHelper.now();

        Predicate<DateEvent> isValidDate = ( DateEvent dataEvent ) -> {

            final boolean isSameData = dataEvent.getDate().isEqual( dhActual );

            if( !isSameData ){
                return false;
            }

            return timeActual.isAfter( dataEvent.getStartTime() ) && timeActual.isBefore( dataEvent.getEndTime() );
        };

        final DateEvent dateEvent = event
        .getDates()
        .stream()
        .filter( isValidDate )
        .findFirst()
        .orElseThrow( () -> new ObjectNotFoundException("Nenhuma data valida do evento encontrada para o checkin!") );

        EventCheckin checkin = new EventCheckin();
        checkin.setDhCheckin( LocalDateTimeHelper.now() );
        checkin.setDhCheckout( null );
        checkin.setIdEvent( idEvent );
        checkin.setNrUuidParticipant( checkinRequestDto.getNrUuidParticipant() );
        checkin.setIdDateEvent( dateEvent.getId() );

        return converter.convertToDto( repository.insert( checkin ) );
    }

}
