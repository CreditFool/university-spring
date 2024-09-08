package com.creditfool.university_spring.dto;

import jakarta.validation.constraints.NotEmpty;

public record SearchByIdDto(@NotEmpty String id) {
}