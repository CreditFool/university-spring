package com.creditfool.university_spring.controller.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;

import com.creditfool.university_spring.dto.response.CommonResponse;
import com.creditfool.university_spring.exception.DataAlreadyExistException;
import com.creditfool.university_spring.exception.DataNotFoundException;
import com.creditfool.university_spring.util.ResponseMakerUtil;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<CommonResponse<Object>> responseStatusException(
            ResponseStatusException e

    ) {
        return ResponseMakerUtil.create(e.getStatusCode(), e.getReason());
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<CommonResponse<Object>> responseDataNotFoundException() {
        return ResponseMakerUtil.create(HttpStatus.NOT_FOUND, "Data not found");
    }

    @ExceptionHandler(DataAlreadyExistException.class)
    public ResponseEntity<CommonResponse<Object>> responseDataAlreadyExistException(DataAlreadyExistException e) {
        return ResponseMakerUtil.create(HttpStatus.CONFLICT, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CommonResponse<Object>> responseMethodArgumentNotValidException(
            MethodArgumentNotValidException e

    ) {
        StringBuilder message = new StringBuilder();
        for (FieldError error : e.getFieldErrors()) {
            message.append(String.valueOf(error.getField() + " " + error.getDefaultMessage() + "; "));
        }
        return ResponseMakerUtil.create(HttpStatus.BAD_REQUEST, message.toString());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<CommonResponse<Object>> responseMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException e

    ) {
        return ResponseMakerUtil.create(HttpStatus.BAD_REQUEST, e.getMessage());
    }

}
