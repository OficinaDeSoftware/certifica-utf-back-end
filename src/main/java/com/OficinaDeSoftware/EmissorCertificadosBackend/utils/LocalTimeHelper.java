package com.OficinaDeSoftware.EmissorCertificadosBackend.utils;

import java.time.LocalTime;
import java.time.ZoneId;

public class LocalTimeHelper {

    static public LocalTime now() {
        return LocalTime.now( ZoneId.systemDefault() );
    }

}
