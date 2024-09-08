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

import com.creditfool.university_spring.dto.SearchByIdDto;
import com.creditfool.university_spring.dto.TeacherDto;
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
        return teacherService.getAllTeacher();
    }

    @GetMapping("teacher")
    public Teacher getTeacherById(@Valid @RequestBody SearchByIdDto dto) {
        return teacherService.getTeacherById(dto.id(), false);
    }

    @PostMapping("teacher")
    @ResponseStatus(HttpStatus.CREATED)
    public Teacher createTeacher(@Valid @RequestBody TeacherDto dto) {
        return teacherService.createTeacher(dto);
    }

    @PutMapping("teacher")
    public Teacher updateTeacher(@Valid @RequestBody TeacherDto dto) {
        return teacherService.updateTeacher(String.valueOf(dto.id()), dto);
    }

    @DeleteMapping("teacher")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTeacher(@Valid @RequestBody SearchByIdDto dto) {
        teacherService.deleteTeacher(dto.id());
    }
}
