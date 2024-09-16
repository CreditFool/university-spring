package com.creditfool.university_spring.mapper;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.creditfool.university_spring.dto.response.CommonResponse;
import com.creditfool.university_spring.dto.response.TeacherDetailResponse;
import com.creditfool.university_spring.entity.Teacher;
import com.creditfool.university_spring.util.ResponseMakerUtil;

@Component
public class TeacherResponseMapper {

    public ResponseEntity<CommonResponse<TeacherDetailResponse>> toTeacherDetailResponse(
            Teacher teacher, HttpStatusCode code, String message

    ) {
        return ResponseMakerUtil.create(
                code,
                message,
                new TeacherDetailResponse(
                        String.valueOf(teacher.getId()),
                        teacher.getFirstName(),
                        teacher.getLastName(),
                        teacher.getNip(),
                        teacher.getAddress(),
                        teacher.getPhone(),
                        teacher.getEmail()

                ));
    }
}
