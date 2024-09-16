package com.creditfool.university_spring.dto.request;

import java.util.UUID;

import com.creditfool.university_spring.util.UUIDValidator;

import jakarta.validation.constraints.NotEmpty;

public record SearchByIdRequest(@NotEmpty String id) {
    public UUID toUUID() {
        return UUIDValidator.isValid(id) ? UUID.fromString(id) : null;
    }
}