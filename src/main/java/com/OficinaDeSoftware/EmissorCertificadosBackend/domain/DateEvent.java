package com.OficinaDeSoftware.EmissorCertificadosBackend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "dates")
public class DateEvent {

    @Id
    private int id;

    private String title;

    private LocalDate date;

    private String startTime;

    private String endTime;

}
