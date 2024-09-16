package com.creditfool.university_spring.util;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import com.creditfool.university_spring.dto.response.CommonResponse;
import com.creditfool.university_spring.dto.response.CommonResponseWithPaging;
import com.creditfool.university_spring.dto.response.PagingResponse;

public class ResponseMakerUtil {

    private ResponseMakerUtil() {
    }

    public static <T> ResponseEntity<CommonResponse<T>> create(HttpStatusCode code, String message, T data) {
        CommonResponse<T> response = new CommonResponse<>(code.value(), message, data);
        return ResponseEntity.status(code).body(response);
    }

    public static <T> ResponseEntity<CommonResponse<T>> create(HttpStatusCode code, String message) {

        CommonResponse<T> response = new CommonResponse<>(code.value(), message, null);
        return ResponseEntity.status(code).body(response);
    }

    public static <T> ResponseEntity<CommonResponseWithPaging<List<T>>> createWithPaging(
            HttpStatusCode code,
            String message,
            Page<T> data,
            Integer page,
            Integer size

    ) {
        CommonResponseWithPaging<List<T>> response = new CommonResponseWithPaging<>(
                code.value(), message, data.getContent(), new PagingResponse(
                        data.getTotalPages(),
                        data.getNumberOfElements(),
                        page,
                        size));

        return ResponseEntity.status(code).body(response);
    }
}
