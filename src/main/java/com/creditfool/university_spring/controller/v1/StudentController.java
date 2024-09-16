package com.creditfool.university_spring.controller.v1;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.creditfool.university_spring.constant.PathApi;
import com.creditfool.university_spring.dto.request.SearchByIdRequest;
import com.creditfool.university_spring.dto.request.StudentCreateUpdateRequest;
import com.creditfool.university_spring.entity.Student;
import com.creditfool.university_spring.service.StudentService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(PathApi.API_V1)
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping(PathApi.STUDENTS)
    public List<Student> getAllStudent() {
        return studentService.getAll();
    }

    @GetMapping(PathApi.STUDENT)
    public Student getStudentById(@Valid @RequestBody SearchByIdRequest request) {
        return studentService.getById(request.toUUID());
    }

    @PostMapping(PathApi.STUDENT)
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@Valid @RequestBody StudentCreateUpdateRequest request) {
        return studentService.create(request.toStudent());
    }

    @PutMapping(PathApi.STUDENT)
    public Student updateStudent(@Valid @RequestBody StudentCreateUpdateRequest request) {
        Student requestEntity = request.toStudent();
        return studentService.update(requestEntity.getId(), requestEntity);
    }

    @DeleteMapping(PathApi.STUDENT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@Valid @RequestBody SearchByIdRequest request) {
        studentService.delete(request.toUUID());
    }
}
