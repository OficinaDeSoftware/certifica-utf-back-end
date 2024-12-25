package com.OficinaDeSoftware.EmissorCertificadosBackend.service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import com.OficinaDeSoftware.EmissorCertificadosBackend.service.uploader.firebase.UploaderFirebaseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OficinaDeSoftware.EmissorCertificadosBackend.converter.EventoConverter;
import com.OficinaDeSoftware.EmissorCertificadosBackend.domain.Certificado;
import com.OficinaDeSoftware.EmissorCertificadosBackend.domain.DateEvent;
import com.OficinaDeSoftware.EmissorCertificadosBackend.domain.Evento;
import com.OficinaDeSoftware.EmissorCertificadosBackend.domain.EventoParticipante;
import com.OficinaDeSoftware.EmissorCertificadosBackend.domain.EventoPersonalizado;
import com.OficinaDeSoftware.EmissorCertificadosBackend.domain.Local;
import com.OficinaDeSoftware.EmissorCertificadosBackend.domain.User;
import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.EventoDto;
import com.OficinaDeSoftware.EmissorCertificadosBackend.repository.CertificadoRepository;
import com.OficinaDeSoftware.EmissorCertificadosBackend.repository.DateEventRepository;
import com.OficinaDeSoftware.EmissorCertificadosBackend.repository.EventoRepository;
import com.OficinaDeSoftware.EmissorCertificadosBackend.service.exception.ObjectNotFoundException;

@Service
public class EventoService {
    
    private final EventoRepository repository;
    private final EventoConverter converter;
    private final UserService userService;
    private final LocalService localService;
    private final CertificadoRepository certificadoRepository;
    private final DateEventRepository dateEventRepository;
    private final EventoParticipanteService participanteService;
    private final UploaderFirebaseService uploaderFirebaseService;

    public EventoService(
            EventoParticipanteService participanteService,
            DateEventRepository dateEventRepository,
            CertificadoRepository certificadoRepository,
            EventoRepository repository, EventoConverter converter,
            UserService userService,
            LocalService localService,
            UploaderFirebaseService uploaderFirebaseService
    ) {
        this.participanteService = participanteService;
        this.dateEventRepository = dateEventRepository;
        this.certificadoRepository = certificadoRepository;
        this.repository = repository;
        this.converter = converter;
        this.userService = userService;
        this.localService = localService;
        this.uploaderFirebaseService = uploaderFirebaseService;
    }

    public List<EventoDto> findAllAsDto() {
        return 
        findAll()
        .stream()
        .map( current -> converter.convertToDto( current ) )
        .collect( Collectors.toList() );
    }

    public List<Evento> findAll() {
        return repository.findAll();
    }

    public List<EventoDto> findAllByNrUuidResponsavelAsDto( String nrUuidResponsavel ) {
        return repository.findAllByNrUuidResponsavel( nrUuidResponsavel )
        .stream()
        .map( current -> converter.convertToDto(current) )
        .collect( Collectors.toList() );
    }

    public List<EventoPersonalizado> findAllPersonalizado() {
        List<Evento> evento = findAll();
        List<User> organizador = userService.findAll();
        List<Local> local = localService.findAll();
        List<EventoPersonalizado> eventoPersonalizado = null;
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        evento.forEach(e -> {
            String org = organizador.stream()
                                   .filter(value -> value.getNrUuid().equals(e.getNrUuidResponsavel()))
                                   .findFirst()
                                   .orElseThrow(() -> new ObjectNotFoundException("Não foi possível obter o organizador"))
                                   .getName();
            
            String loc = local.stream()
                             .filter(value -> value.getIdLocal().equals(e.getIdLocal()))
                             .findFirst()
                             .orElseThrow(() -> new ObjectNotFoundException("Não foi possível obter o local"))
                             .getDsAuditorio();
        
            eventoPersonalizado.add(new EventoPersonalizado(
                    e.getDsNome(),
                    e.getDsInformacoes(),
                    e.getDhInicio().format(dateFormatter),
                    e.getDhInicio().format(timeFormatter),
                    e.getDhFim().format(dateFormatter),
                    e.getDhFim().format(timeFormatter),
                    loc,
                    org,
                    e.getIdEvento()
            ));
        });

        return eventoPersonalizado;
    }

    public EventoDto findByIdAsDto( String id ) {
        return converter.convertToDto( findById( id ) );
    }

    public Evento findById(String codigo) {

        Evento event = repository.findById(codigo).orElseThrow(() -> new ObjectNotFoundException("Evento não encontrado"));

        final List<EventoParticipante> participantes = participanteService.findAllByIdEvento(codigo);

        final List<User> usuariosParticipantes = participantes
        .stream()
        .map( current -> userService.findByNrUuid( current.getNrUuidParticipante() ) )
        .collect( Collectors.toList() );

        event.setParticipantes( usuariosParticipantes );

        return event;

    }

    public EventoDto insert( EventoDto evento ) {

        final String dsBackgroundImageUrl = uploaderFirebaseService.image( evento.getBackgroundImage() );

        Evento event = converter.convertToEntity(evento);

        event.setDsBackgroundImageUrl( dsBackgroundImageUrl );
        
        Evento newEvent = repository.insert( event );

        List<DateEvent> dates = newEvent.getDates();

        if( dates != null && !dates.isEmpty() ) {
            newEvent.getDates().forEach( ( item ) -> {
                item.setIdEvento( newEvent.getIdEvento() );
                dateEventRepository.save( item );
            });
        }

        Certificado certificado = newEvent.getCertificado();

        if( certificado != null ) {
            certificado.setIdEvento( newEvent.getIdEvento() );
            certificadoRepository.save( certificado );
        }

        return converter.convertToDto(newEvent);
    }

    public Evento update(EventoDto evento) {
        Evento eventoAtualizado = findById(evento.getIdEvento());
        BeanUtils.copyProperties(evento, eventoAtualizado);
        return repository.save(eventoAtualizado);
    }

    public void delete(String codigo) {
        findById(codigo);
        repository.deleteById(codigo);
    }
}
