package com.OficinaDeSoftware.EmissorCertificadosBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@SpringBootApplication
@OpenAPIDefinition( info = @Info( title = "Emissor de certificados", version = "1.0", description = "API desenvolvida com o objetivo de gerar eventos que podem emitir certificados" ))
@EnableScheduling
@EnableFeignClients
public class EmissorCertificadosBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmissorCertificadosBackendApplication.class, args);
	}

}
