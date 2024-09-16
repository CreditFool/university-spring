package com.creditfool.university_spring.dto.response;

public record CommonResponse<T>(
        Integer statusCode,
        String message,
        T data

) {
}
