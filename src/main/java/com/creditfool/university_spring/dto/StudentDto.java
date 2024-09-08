package com.creditfool.university_spring.dto;

import java.util.UUID;

import com.creditfool.university_spring.util.customvalidation.PhoneNumber;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record StudentDto(
        UUID id,
        @NotBlank String firstName,
        String lastName,
        @NotBlank String nim,
        @NotBlank String address,
        @PhoneNumber String phone,
        @Email String email) {
}
