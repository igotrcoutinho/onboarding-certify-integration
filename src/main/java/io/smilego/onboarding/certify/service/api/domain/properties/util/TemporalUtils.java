package io.smilego.onboarding.certify.service.api.domain.properties.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class TemporalUtils {

    private TemporalUtils() {}

    public static LocalDate formatAsLocalDate(String value) {
        return LocalDate.parse(value, DateTimeFormatter.ISO_DATE);
    }

    public static LocalDateTime formatAsLocalDateTime(String value) {
        return LocalDateTime.parse(value, DateTimeFormatter.ISO_DATE_TIME);
    }

    public static String formatLocalDateAsString(Object object) {
        return ((LocalDate) object).format(DateTimeFormatter.ISO_DATE);
    }

    public static String formatLocalDateTimeAsString(Object object) {
        return ((LocalDateTime) object).format(DateTimeFormatter.ISO_DATE_TIME);
    }

}
