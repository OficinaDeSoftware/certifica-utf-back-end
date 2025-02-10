package com.OficinaDeSoftware.EmissorCertificadosBackend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "dates")
public class DateEvent {

    private String title;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

}
