package com.OficinaDeSoftware.EmissorCertificadosBackend.dto.response;

import java.time.LocalDate;
import java.util.List;

import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.CertificadoDto;
import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.DateEventDto;
import com.OficinaDeSoftware.EmissorCertificadosBackend.dto.LocalDto;
import com.fasterxml.jackson.annotation.*;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventResponseDto {
    
    @Id
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("id")
    private String idEvent;

    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate endDate;

    private List<DateEventDto> dates;

    private CertificadoDto certificate;

    private Integer workload;

    private String description;

    private LocalDto location;

    private String backgroundUrl;

}

