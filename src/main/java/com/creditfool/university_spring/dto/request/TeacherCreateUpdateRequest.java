package com.creditfool.university_spring.dto.request;

import java.util.UUID;

import com.creditfool.university_spring.entity.Teacher;
import com.creditfool.university_spring.util.UUIDValidator;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TeacherCreateUpdateRequest(
        String id,
        @NotBlank String firstName,
        String lastName,
        @NotBlank String nip,
        @NotBlank String address,
        @Size(min = 11, max = 13) @NotBlank String phone,
        @Email @NotBlank String email

) {

    public Teacher toTeacher() {
        return Teacher.builder()
                .id(UUIDValidator.isValid(id) ? UUID.fromString(id) : null)
                .firstName(firstName.trim())
                .lastName(lastName.trim())
                .nip(nip.trim())
                .address(address.trim())
                .phone(phone.trim())
                .email(email.trim())
                .build();
    }
}
