package com.creditfool.university_spring.dto.request;

import java.util.UUID;

import com.creditfool.university_spring.entity.Student;
import com.creditfool.university_spring.util.UUIDValidator;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record StudentCreateUpdateRequest(
        String id,
        @NotBlank String firstName,
        String lastName,
        @NotBlank String nim,
        @NotBlank String address,
        @Size(min = 11, max = 13) @NotBlank String phone,
        @Email @NotBlank String email

) {

    public Student toStudent() {
        return Student.builder()
                .id(UUIDValidator.isValid(id) ? UUID.fromString(id) : null)
                .firstName(firstName.trim())
                .lastName(lastName.trim())
                .nim(nim.trim())
                .address(address.trim())
                .phone(phone.trim())
                .email(email.trim())
                .build();
    }
}
