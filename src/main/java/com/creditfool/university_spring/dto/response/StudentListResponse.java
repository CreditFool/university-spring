package com.creditfool.university_spring.dto.response;

import java.util.UUID;

public record StudentListResponse(
        UUID id,
        String firstName,
        String lastName,
        String nim

) {
}
