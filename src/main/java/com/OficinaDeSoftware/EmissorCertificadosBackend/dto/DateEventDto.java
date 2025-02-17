package com.OficinaDeSoftware.EmissorCertificadosBackend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class DateEventDto {

        private int id;

        private String title;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        private LocalDate date;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
        private String startTime;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
        private String endTime;

}