package com.barbosa.wolfChat.utils.CommonUtil;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public abstract class CommunUtils {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").withZone(ZoneId.of("America/Sao_Paulo"));

    public static String getDateTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
    public static Instant getInstance() {
        return Instant.now();
    }
    public static String getFormattedInstante() {
        Instant instant = getInstance();
        return DATE_TIME_FORMATTER.format(instant);
    }

    public static String AUTHORIZATION_HEADER = "Authorization";
    public static String BEARER_TOKEN = "Bearer ";
}
