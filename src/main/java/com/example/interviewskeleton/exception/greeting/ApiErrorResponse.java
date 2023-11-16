package com.example.interviewskeleton.exception.greeting;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ApiErrorResponse {

    private String message;
}
