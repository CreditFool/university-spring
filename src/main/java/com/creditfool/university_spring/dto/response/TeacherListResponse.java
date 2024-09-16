package com.creditfool.university_spring.dto.response;

import java.util.UUID;

public record TeacherListResponse(
        UUID id,
        String firstName,
        String lastName,
        String nip

) {
}
