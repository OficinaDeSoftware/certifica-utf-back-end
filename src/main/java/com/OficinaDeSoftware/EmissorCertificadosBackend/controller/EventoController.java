package com.OficinaDeSoftware.EmissorCertificadosBackend.controller;

import java.util.List;

import com.OficinaDeSoftware.EmissorCertificadosBackend.service.uploader.firebase.UploaderFirebaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.OficinaDeSoftware.EmissorCertificadosBackend.domain.EventoPersonalizado;
import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.EventoDto;
import com.OficinaDeSoftware.EmissorCertificadosBackend.service.EventoService;

@RestController
@RequestMapping("/api/evento")
public class EventoController {
    
    private final EventoService service;

    public EventoController(EventoService service, UploaderFirebaseService uploaderFirebaseService) {
        this.service = service;
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<EventoDto> findOne( @PathVariable String id ){
        return ResponseEntity.ok().body( service.findByIdAsDto( id ) );
    }

    @GetMapping( "/findAll/{nrUuidResponsavel}" )
    public ResponseEntity<List<EventoDto>> findAllByNrUuidResponsavel( @PathVariable String nrUuidResponsavel ) {
        return ResponseEntity.ok().body( service.findAllByNrUuidResponsavelAsDto( nrUuidResponsavel ) );
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<List<EventoDto>> findAll() {
        return ResponseEntity.ok().body(service.findAllAsDto());
    }

    @GetMapping(value = "/personalized/findAll")
    public ResponseEntity<List<EventoPersonalizado>> findAllPersonalizado() {
        return ResponseEntity.ok().body(service.findAllPersonalizado());
    }

    @PostMapping
    public ResponseEntity<EventoDto> insert( @RequestBody EventoDto event ) {
        return ResponseEntity.status( HttpStatus.CREATED ).body( service.insert( event ) );
    }
}

// {
//     "event": {
//         "name": "Evento",
//         "dateStart": "2023-11-09T13:00",
//         "dateEnd": "2023-11-10T18:00",
//         "dates": [
//             {
//                 "date": "2023-11-09",
//                 "startTime": "13:30",
//                 "endTime": "18:00"
//             },
//             {
//                 "date": "2023-11-09",
//                 "startTime": "19:00",
//                 "endTime": "21:00"
//             },
//             {
//                 "date": "2023-11-10",
//                 "startTime": "10:00",
//                 "endTime": "12:00"
//             },
//             {
//                 "date": "2023-11-10",
//                 "startTime": "13:00",
//                 "endTime": "17:59"
//             }
//         ],
//         "workload": "8",
//         "informations": "5",
//         "nomeEmissor": "ALVARO EDUARDO MENEGON ROSARIO",
//         "certificate": {
//             "htmlModel": "",
//             "modelo": "1",
//             "personalData": {
//                 "instituicao": "Universidade Tecnológica Federal do Paraná",
//                 "local": "",
//                 "logo": {},
//                 "backgroundImage": {}
//             }
//         }
//     }
// }