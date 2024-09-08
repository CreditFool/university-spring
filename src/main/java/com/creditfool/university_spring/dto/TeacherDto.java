package com.creditfool.university_spring.dto;

import java.util.UUID;

import com.creditfool.university_spring.util.customvalidation.PhoneNumber;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record TeacherDto(
        UUID id,
        @NotBlank String firstName,
        String lastName,
        @NotBlank String nip,
        @NotBlank String address,
        @PhoneNumber @NotBlank String phone,
        @Email @NotBlank String email) {
}
