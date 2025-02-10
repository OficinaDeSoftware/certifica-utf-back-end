package com.OficinaDeSoftware.EmissorCertificadosBackend.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class LocalDateTimeHelper {

    static public LocalDateTime now() {
        return LocalDateTime.now( ZoneId.systemDefault() );
    }

}
