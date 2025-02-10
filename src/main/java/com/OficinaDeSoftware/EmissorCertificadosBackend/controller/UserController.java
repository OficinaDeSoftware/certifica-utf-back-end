package com.OficinaDeSoftware.EmissorCertificadosBackend.controller;

import java.util.List;

import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.response.UserResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;

import com.OficinaDeSoftware.EmissorCertificadosBackend.domain.User;
import com.OficinaDeSoftware.EmissorCertificadosBackend.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/findAll")
    public List<User> user(){
        return service.findAll();
    }

    @GetMapping("/{nrUuid}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable String nrUuid){
        return ResponseEntity.ok(service.getByNrUuid(nrUuid));
    }

}
