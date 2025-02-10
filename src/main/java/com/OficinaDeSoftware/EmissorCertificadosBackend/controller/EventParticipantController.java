package com.OficinaDeSoftware.EmissorCertificadosBackend.controller;

import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.response.MessageResponse;
import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.response.UserResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.EventParticipantDto;
import com.OficinaDeSoftware.EmissorCertificadosBackend.service.EventParticipantService;

import java.util.List;

@RestController
@RequestMapping("/api/event/participant")
public class EventParticipantController {

    private final EventParticipantService service;

    public EventParticipantController( EventParticipantService service ) {
        this.service = service;
    }

    @PostMapping("/{nrUuidParticipant}/subscribe")
    public ResponseEntity<UserResponseDto> insert( @PathVariable String nrUuidParticipant, @RequestBody EventParticipantDto participant ) {
        return ResponseEntity.status( HttpStatus.CREATED ).body( service.insert( nrUuidParticipant, participant ) );
    }

    @DeleteMapping( "/{nrUuidParticipant}/remove" )
    public ResponseEntity<MessageResponse> remove(@PathVariable String nrUuidParticipant, @RequestBody EventParticipantDto participant ) {
        service.remove( nrUuidParticipant, participant );
        return ResponseEntity.ok().body( new MessageResponse( "Participant removido com sucesso!" ) );
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAllByIdEvent( @RequestParam String idEvent ) {
        return ResponseEntity.ok().body( service.findAllByIdEvent( idEvent ) );
    }
    
}
