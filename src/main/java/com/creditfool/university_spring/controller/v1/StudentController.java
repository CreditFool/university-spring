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
import com.creditfool.university_spring.dto.StudentDto;
import com.creditfool.university_spring.entity.Student;
import com.creditfool.university_spring.service.StudentService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("students")
    public List<Student> getAllStudent() {
        return studentService.getAllStudent();
    }

    @GetMapping("student")
    public Student getStudentById(@Valid @RequestBody SearchByIdDto dto) {
        return studentService.getStudentById(dto.id(), false);
    }

    @PostMapping("student")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@Valid @RequestBody StudentDto dto) {
        return studentService.createStudent(dto);
    }

    @PutMapping("student")
    public Student updateStudent(@Valid @RequestBody StudentDto dto) {
        return studentService.updateStudent(String.valueOf(dto.id()), dto);
    }

    @DeleteMapping("student")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@Valid @RequestBody SearchByIdDto dto) {
        studentService.deleteStudent(dto.id());
    }
}
