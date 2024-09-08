package com.creditfool.university_spring.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;

public record SubjectDto(
        UUID id,
        @NotBlank String subjectName) {
}
