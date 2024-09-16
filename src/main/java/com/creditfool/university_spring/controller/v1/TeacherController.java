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
import org.springframework.web.bind.annotation.RestController;

import com.creditfool.university_spring.constant.PathApi;
import com.creditfool.university_spring.dto.request.SearchByIdRequest;
import com.creditfool.university_spring.dto.request.TeacherCreateUpdateRequest;
import com.creditfool.university_spring.dto.response.CommonResponse;
import com.creditfool.university_spring.dto.response.CommonResponseWithPaging;
import com.creditfool.university_spring.dto.response.TeacherDetailResponse;
import com.creditfool.university_spring.dto.response.TeacherListResponse;
import com.creditfool.university_spring.entity.Teacher;
import com.creditfool.university_spring.mapper.TeacherResponseMapper;
import com.creditfool.university_spring.service.TeacherService;
import com.creditfool.university_spring.util.ResponseMakerUtil;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(PathApi.API_V1)
@AllArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;
    private final TeacherResponseMapper mapper;

    @GetMapping(PathApi.TEACHERS)
    public ResponseEntity<CommonResponseWithPaging<List<TeacherListResponse>>> getAllTeacher(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "5") Integer size,
            @RequestParam(required = false, defaultValue = "true") boolean isActive

    ) {
        return ResponseMakerUtil.createWithPaging(
                HttpStatus.OK,
                "Data fetched",
                teacherService.getAllForListResponse(page, size, isActive),
                page,
                size);
    }

    @GetMapping(PathApi.TEACHER)
    public ResponseEntity<CommonResponse<TeacherDetailResponse>> getTeacherById(
            @Valid @RequestBody SearchByIdRequest request

    ) {
        return mapper.toTeacherDetailResponse(
                teacherService.getById(request.toUUID(), true),
                HttpStatus.OK,
                "Data fetched");
    }

    @PostMapping(PathApi.TEACHER)
    public ResponseEntity<CommonResponse<TeacherDetailResponse>> createTeacher(
            @Valid @RequestBody TeacherCreateUpdateRequest request

    ) {
        return mapper.toTeacherDetailResponse(
                teacherService.create(request.toTeacher()),
                HttpStatus.CREATED,
                "Data created");
    }

    @PutMapping(PathApi.TEACHER)
    public ResponseEntity<CommonResponse<TeacherDetailResponse>> updateTeacher(
            @Valid @RequestBody TeacherCreateUpdateRequest request

    ) {
        Teacher requestEntity = request.toTeacher();
        return mapper.toTeacherDetailResponse(
                teacherService.update(requestEntity.getId(), requestEntity),
                HttpStatus.OK,
                "Data updated");
    }

    @DeleteMapping(PathApi.TEACHER)
    public ResponseEntity<CommonResponse<Object>> deleteTeacher(
            @Valid @RequestBody SearchByIdRequest request

    ) {
        teacherService.delete(request.toUUID());
        return ResponseMakerUtil.create(HttpStatus.OK, "Data deleted");
    }
}
