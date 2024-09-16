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

import com.creditfool.university_spring.dto.request.SearchByIdRequest;
import com.creditfool.university_spring.dto.request.TeacherCreateUpdateRequest;
import com.creditfool.university_spring.entity.Teacher;
import com.creditfool.university_spring.service.TeacherService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping("teachers")
    public List<Teacher> getAllTeacher() {
        return teacherService.getAll();
    }

    @GetMapping("teacher")
    public Teacher getTeacherById(@Valid @RequestBody SearchByIdRequest request) {
        return teacherService.getById(request.toUUID());
    }

    @PostMapping("teacher")
    @ResponseStatus(HttpStatus.CREATED)
    public Teacher createTeacher(@Valid @RequestBody TeacherCreateUpdateRequest request) {
        return teacherService.create(request.toTeacher());
    }

    @PutMapping("teacher")
    public Teacher updateTeacher(@Valid @RequestBody TeacherCreateUpdateRequest request) {
        Teacher requestEntity = request.toTeacher();
        return teacherService.update(requestEntity.getId(), requestEntity);
    }

    @DeleteMapping("teacher")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTeacher(@Valid @RequestBody SearchByIdRequest request) {
        teacherService.delete(request.toUUID());
    }
}
