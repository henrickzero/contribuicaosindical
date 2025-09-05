package com.brcodigo.app.impl.util;

import lombok.experimental.UtilityClass;

import java.time.*;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class DateUtil {
    private static final String ZONE_ID = "America/Sao_Paulo";
    private static final String BR_FORMAT = "dd/MM/yyyy HH:mm:ss";

    public static String localDateTimeToString(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(BR_FORMAT);
        return localDateTime.format(formatter);
    }

    public static String isoToString(String dateIso) {
        Instant instant = Instant.parse(dateIso);
        ZoneId zonaSaoPaulo = ZoneId.of(ZONE_ID);
        ZonedDateTime zonedDateTimeSaoPaulo = instant.atZone(zonaSaoPaulo);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(BR_FORMAT);
        return zonedDateTimeSaoPaulo.format(formatter);
    }

    public static LocalDateTime isoToLocalDateTime(String dateIso) {
        Instant instant = Instant.parse(dateIso);
        ZoneId zonaSaoPaulo = ZoneId.of(ZONE_ID);
        ZonedDateTime zonedDateTimeSaoPaulo = instant.atZone(zonaSaoPaulo);
        return zonedDateTimeSaoPaulo.toLocalDateTime();
    }

    public static LocalDate isoToLocalDate(String dateIso) {
        Instant instant = Instant.parse(dateIso);
        ZoneId zonaSaoPaulo = ZoneId.of(ZONE_ID);
        ZonedDateTime zonedDateTimeSaoPaulo = instant.atZone(zonaSaoPaulo);
        return zonedDateTimeSaoPaulo.toLocalDate();
    }

}
