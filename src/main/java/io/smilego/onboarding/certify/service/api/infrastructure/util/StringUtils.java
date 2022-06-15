package io.smilego.onboarding.certify.service.api.infrastructure.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Objects;

public class StringUtils {

    public static boolean isNullOrEmpty(String string) {
        return Objects.isNull(string) || string.isEmpty();
    }

    public static String substring(String string, int beginIndex, int endIndex) {
        return Objects.nonNull(string) ? (string.length() > endIndex ? string.substring(beginIndex, endIndex) : string) : null;
    }

    public static String truncate(String string, int maxSize) {
        return substring(string, 0, maxSize);
    }

    public static String writeValueAsString(Object value) {
        try {
            return new ObjectMapper().writeValueAsString(value);
        } catch (Exception exception) {
            return null;
        }

    }

}
