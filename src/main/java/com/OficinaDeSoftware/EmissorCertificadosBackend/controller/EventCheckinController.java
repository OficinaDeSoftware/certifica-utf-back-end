package com.OficinaDeSoftware.EmissorCertificadosBackend.controller;

import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.EventCheckinDto;
import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.request.EventCheckinRequestDto;
import com.OficinaDeSoftware.EmissorCertificadosBackend.service.EventCheckinService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/api/event")
public class EventCheckinController {

    private final EventCheckinService eventCheckinService;

    public EventCheckinController( final EventCheckinService eventCheckinService) {
        this.eventCheckinService = eventCheckinService;
    }

    @PostMapping("/{idEvent}/checkin")
    ResponseEntity<EventCheckinDto> checkin(@PathVariable String idEvent, @RequestBody EventCheckinRequestDto checkinRequestDto ) {
        return ResponseEntity.ok().body( eventCheckinService.checkin( idEvent, checkinRequestDto ) );
    }

    @PostMapping("/{idEvent}/checkout")
    ResponseEntity<EventCheckinDto> checkout(@PathVariable String idEvent, @RequestBody EventCheckinRequestDto checkinRequestDto ) {
        return ResponseEntity.ok().body( eventCheckinService.checkout( idEvent, checkinRequestDto ) );
    }

}
