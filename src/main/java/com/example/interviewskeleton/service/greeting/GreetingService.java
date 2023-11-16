package com.example.interviewskeleton.service.greeting;

import com.example.interviewskeleton.enums.LocaleEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class GreetingService {

    @Value("${greeting.morning.en}")
    private String morningEn;

    @Value("${greeting.afternoon.en}")
    private String afternoonEn;

    @Value("${greeting.evening.en}")
    private String eveningEn;

    @Value("${greeting.morning.es}")
    private String morningEs;

    @Value("${greeting.afternoon.es}")
    private String afternoonEs;

    @Value("${greeting.evening.es}")
    private String eveningEs;

    public String getGreeting(String name, LocaleEnum locale) {
        LocalTime now = LocalTime.now();

        String greeting;
        if (now.isBefore(LocalTime.NOON)) {
            greeting = (locale == LocaleEnum.EN) ? morningEn : morningEs;
        } else if (now.isBefore(LocalTime.of(17, 0))) {
            greeting = (locale == LocaleEnum.EN) ? afternoonEn : afternoonEs;
        } else {
            greeting = (locale == LocaleEnum.EN) ? eveningEn : eveningEs;
        }

        return greeting.replace("{name}", name);
    }
}
