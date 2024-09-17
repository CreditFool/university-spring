package com.creditfool.university_spring.mapper;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.creditfool.university_spring.dto.response.CommonResponse;
import com.creditfool.university_spring.dto.response.SubjectResponse;
import com.creditfool.university_spring.entity.Subject;
import com.creditfool.university_spring.util.ResponseMakerUtil;

@Component
public class SubjectResponseMapper {

    public ResponseEntity<CommonResponse<SubjectResponse>> toSubjectDetailResponse(
            Subject subject, HttpStatusCode code, String message

    ) {
        return ResponseMakerUtil.create(
                code,
                message,
                new SubjectResponse(
                        subject.getId(),
                        subject.getName()

                ));
    }
}
