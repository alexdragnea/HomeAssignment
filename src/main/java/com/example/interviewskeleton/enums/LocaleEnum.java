package com.example.interviewskeleton.enums;

import com.example.interviewskeleton.exception.greeting.UnsupportedLocaleEx;

import java.util.Arrays;

public enum LocaleEnum {
    EN("en"),
    ES("es");

    private final String value;

    LocaleEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static LocaleEnum fromValue(String value) {
        return Arrays.stream(values())
                .filter(enumValue -> enumValue.getValue().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new UnsupportedLocaleEx("Unsupported locale: " + value));
    }
}
