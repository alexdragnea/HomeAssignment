package com.example.interviewskeleton.controller;

import com.example.interviewskeleton.enums.LocaleEnum;
import com.example.interviewskeleton.service.greeting.GreetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greet")
@RequiredArgsConstructor
public class GreetingController {

    private final GreetingService greetingService;

    @GetMapping("/{name}")
    public String greet(@PathVariable String name, String locale) {
        return greetingService.getGreeting(name, LocaleEnum.fromValue(locale));
    }
}
