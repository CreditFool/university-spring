package com.creditfool.university_spring.dto.response;

public record StudentDetailResponse(
                String id,
                String firstName,
                String lastName,
                String nim,
                String address,
                String phone,
                String email

) {
}
