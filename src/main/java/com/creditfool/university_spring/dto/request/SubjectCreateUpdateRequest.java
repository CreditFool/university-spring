package com.creditfool.university_spring.dto.request;

import java.util.UUID;

import com.creditfool.university_spring.entity.Subject;

import jakarta.validation.constraints.NotBlank;

public record SubjectCreateUpdateRequest(
        UUID id,
        @NotBlank String name

) {
    public Subject toSubject() {
        return Subject.builder()
                .id(id)
                .name(name.trim())
                .build();
    }
}
