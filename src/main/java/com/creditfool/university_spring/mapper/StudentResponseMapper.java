package com.creditfool.university_spring.mapper;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.creditfool.university_spring.dto.response.CommonResponse;
import com.creditfool.university_spring.dto.response.StudentDetailResponse;
import com.creditfool.university_spring.entity.Student;
import com.creditfool.university_spring.util.ResponseMakerUtil;

@Component
public class StudentResponseMapper {

    public ResponseEntity<CommonResponse<StudentDetailResponse>> toStudentDetailResponse(
            Student student, HttpStatusCode code, String message

    ) {
        return ResponseMakerUtil.create(
                code,
                message,
                new StudentDetailResponse(
                        String.valueOf(student.getId()),
                        student.getFirstName(),
                        student.getLastName(),
                        student.getNim(),
                        student.getAddress(),
                        student.getPhone(),
                        student.getEmail()

                ));
    }
}
