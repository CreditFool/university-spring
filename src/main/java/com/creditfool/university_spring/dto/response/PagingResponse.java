package com.creditfool.university_spring.dto.response;

public record PagingResponse(
        int totalPages,
        int count,
        Integer page,
        Integer size

) {
}
