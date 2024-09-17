package com.creditfool.university_spring.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PageAndSizeValidatorUtil {
    private PageAndSizeValidatorUtil() {
    }

    public static void validate(int page, int size) {
        if (page < 1 || size < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Page and Size need to greater than 1");
        }
    }
}
