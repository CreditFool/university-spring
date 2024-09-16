package com.creditfool.university_spring.dto.response;

public record CommonResponseWithPaging<T>(
        Integer statusCode,
        String message,
        T data,
        PagingResponse paging

) {
}
