package com.creditfool.university_spring.dto;

import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record TeacherDto(
        UUID id,

        @NotEmpty String firstName,

        String lastName,

        @NotEmpty String nip,

        @NotEmpty String address,

        @NotEmpty String phone,

        @Email String email) {
}
