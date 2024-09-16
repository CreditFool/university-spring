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
import com.creditfool.university_spring.dto.request.StudentCreateUpdateRequest;
import com.creditfool.university_spring.dto.response.CommonResponse;
import com.creditfool.university_spring.dto.response.CommonResponseWithPaging;
import com.creditfool.university_spring.dto.response.StudentDetailResponse;
import com.creditfool.university_spring.dto.response.StudentListResponse;
import com.creditfool.university_spring.entity.Student;
import com.creditfool.university_spring.mapper.StudentResponseMapper;
import com.creditfool.university_spring.service.StudentService;
import com.creditfool.university_spring.util.ResponseMakerUtil;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(PathApi.API_V1)
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final StudentResponseMapper mapper;

    @GetMapping(PathApi.STUDENTS)
    public ResponseEntity<CommonResponseWithPaging<List<StudentListResponse>>> getAllStudent(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "5") Integer size,
            @RequestParam(required = false, defaultValue = "true") boolean isActive

    ) {

        return ResponseMakerUtil.createWithPaging(
                HttpStatus.OK,
                "Data fetched",
                studentService.getAllForListResponse(page, size, isActive),
                page,
                size);
    }

    @GetMapping(PathApi.STUDENT)
    public ResponseEntity<CommonResponse<StudentDetailResponse>> getStudentById(
            @Valid @RequestBody SearchByIdRequest request

    ) {
        return mapper.toStudentDetailResponse(
                studentService.getById(request.toUUID(), true),
                HttpStatus.OK,
                "Data fetched");
    }

    @PostMapping(PathApi.STUDENT)
    public ResponseEntity<CommonResponse<StudentDetailResponse>> createStudent(
            @Valid @RequestBody StudentCreateUpdateRequest request

    ) {
        return mapper.toStudentDetailResponse(
                studentService.create(request.toStudent()),
                HttpStatus.CREATED,
                "Student data created");
    }

    @PutMapping(PathApi.STUDENT)
    public ResponseEntity<CommonResponse<StudentDetailResponse>> updateStudent(
            @Valid @RequestBody StudentCreateUpdateRequest request

    ) {
        Student requestEntity = request.toStudent();
        return mapper.toStudentDetailResponse(
                studentService.update(requestEntity.getId(), requestEntity),
                HttpStatus.OK,
                "Data updated");
    }

    @DeleteMapping(PathApi.STUDENT)
    public ResponseEntity<CommonResponse<Object>> deleteStudent(
            @Valid @RequestBody SearchByIdRequest request

    ) {
        studentService.delete(request.toUUID());
        return ResponseMakerUtil.create(HttpStatus.OK, "Data deleted");
    }
}
