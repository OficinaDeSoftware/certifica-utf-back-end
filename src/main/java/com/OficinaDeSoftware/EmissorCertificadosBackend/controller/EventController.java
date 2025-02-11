package com.OficinaDeSoftware.EmissorCertificadosBackend.controller;

import java.util.List;
import java.util.Map;

import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.response.EventBasicResponseDto;
import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.request.EventRequestDto;
import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.response.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.response.EventResponseDto;
import com.OficinaDeSoftware.EmissorCertificadosBackend.service.EventService;

@RestController
@RequestMapping("/api/event")
public class EventController {
    
    private final EventService service;

    public EventController(EventService service ) {
        this.service = service;
    }

    @GetMapping( "/{idEvent}" )
    public ResponseEntity<EventResponseDto> findOne(@PathVariable String idEvent ){
        return ResponseEntity.ok().body( service.findById( idEvent ) );
    }

    @GetMapping( "/findAll/{nrUuidResponsible}" )
    public ResponseEntity<List<EventResponseDto>> findAllByNrUuidResponsible( @PathVariable String nrUuidResponsible ) {
        return ResponseEntity.ok().body( service.findAllByNrUuidResponsible( nrUuidResponsible ) );
    }

    @GetMapping
    public ResponseEntity<List<EventBasicResponseDto>> findAll( @RequestParam( required = false ) Map<String, String> params ) {
        return ResponseEntity.ok().body( service.findAll( params ) );
    }

    @PostMapping
    public ResponseEntity<EventResponseDto> insert(@RequestBody EventRequestDto eventRequest ) {
        return ResponseEntity.ok().body( service.insert( eventRequest ) );
    }

    @PutMapping("/{idEvent}")
    public ResponseEntity<EventResponseDto> update(@PathVariable String idEvent, @RequestBody EventRequestDto eventRequest) {
        return ResponseEntity.ok().body( service.update( idEvent, eventRequest ) );
    }

    @DeleteMapping("/{idEvent}")
    public ResponseEntity<MessageResponse> delete(@PathVariable String idEvent) {
        service.delete( idEvent );
        return ResponseEntity.ok().body( new MessageResponse( "Evento deletado com sucesso!" ) );
    }

    @PostMapping("/{idEvent}/finished")
    public ResponseEntity<MessageResponse> update( @PathVariable String idEvent ) {
        service.finished( idEvent );
        return ResponseEntity.ok().body( new MessageResponse("Evento finalizado com sucesso!" ) );
    }

}
