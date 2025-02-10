package com.OficinaDeSoftware.EmissorCertificadosBackend.utils;

import java.time.LocalDate;
import java.time.ZoneId;

public class LocalDateHelper {

    static public LocalDate now() {
        return LocalDate.now(ZoneId.systemDefault());
    }

}
