package com.creditfool.university_spring.dto;

import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record TeacherDto(
                UUID id,
                @NotBlank String firstName,
                String lastName,
                @NotBlank String nip,
                @NotBlank String address,
                @NotBlank String phone,
                @Email String email) {
}
