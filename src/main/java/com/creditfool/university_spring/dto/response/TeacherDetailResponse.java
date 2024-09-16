package com.creditfool.university_spring.dto.response;

public record TeacherDetailResponse(
        String id,
        String firstName,
        String lastName,
        String nip,
        String address,
        String phone,
        String email

) {
}
