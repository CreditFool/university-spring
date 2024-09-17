package com.creditfool.university_spring.controller.v1;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.creditfool.university_spring.constant.PathApi;
import com.creditfool.university_spring.dto.request.SearchByIdRequest;
import com.creditfool.university_spring.dto.request.SubjectCreateUpdateRequest;
import com.creditfool.university_spring.dto.response.CommonResponse;
import com.creditfool.university_spring.dto.response.CommonResponseWithPaging;
import com.creditfool.university_spring.dto.response.SubjectResponse;
import com.creditfool.university_spring.entity.Subject;
import com.creditfool.university_spring.mapper.SubjectResponseMapper;
import com.creditfool.university_spring.service.SubjectService;
import com.creditfool.university_spring.util.PageAndSizeValidatorUtil;
import com.creditfool.university_spring.util.ResponseMakerUtil;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(PathApi.API_V1)
@AllArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;
    private final SubjectResponseMapper mapper;

    @GetMapping(PathApi.SUBJECTS)
    public ResponseEntity<CommonResponseWithPaging<List<SubjectResponse>>> getAllSubject(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "5") Integer size,
            @RequestParam(required = false, defaultValue = "true") boolean isActive

    ) {
        PageAndSizeValidatorUtil.validate(page, size);
        return ResponseMakerUtil.createWithPaging(
                HttpStatus.OK,
                "Data fetched",
                subjectService.getAllForListResponse(page, size, isActive),
                page,
                size);
    }

    @GetMapping(PathApi.SUBJECT)
    public ResponseEntity<CommonResponse<SubjectResponse>> getSubjectById(
            @Valid @RequestBody SearchByIdRequest request

    ) {
        return mapper.toSubjectDetailResponse(
                subjectService.getById(request.toUUID(), true),
                HttpStatus.OK,
                "Data fetched"

        );
    }

    @PostMapping(PathApi.SUBJECT)
    public ResponseEntity<CommonResponse<SubjectResponse>> createSubject(
            @Valid @RequestBody SubjectCreateUpdateRequest request

    ) {
        return mapper.toSubjectDetailResponse(
                subjectService.create(request.toSubject()),
                HttpStatus.CREATED,
                "Data created"

        );
    }

    @PutMapping(PathApi.SUBJECT)
    public ResponseEntity<CommonResponse<SubjectResponse>> updateSubject(
            @Valid @RequestBody SubjectCreateUpdateRequest request

    ) {
        Subject requestEntity = request.toSubject();
        return mapper.toSubjectDetailResponse(
                subjectService.update(requestEntity.getId(), requestEntity),
                HttpStatus.OK,
                "Data Updated"

        );
    }

    @DeleteMapping(PathApi.SUBJECT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<CommonResponse<Object>> deleteSubject(
            @Valid @RequestBody SearchByIdRequest request

    ) {
        subjectService.delete(request.toUUID());
        return ResponseMakerUtil.create(HttpStatus.OK, "Data deleted");
    }
}
