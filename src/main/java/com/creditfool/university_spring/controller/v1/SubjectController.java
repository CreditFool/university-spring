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
import com.creditfool.university_spring.dto.SubjectDto;
import com.creditfool.university_spring.entity.Subject;
import com.creditfool.university_spring.service.SubjectService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @GetMapping("subjects")
    public List<Subject> getAllSubject() {
        return subjectService.getAllSubject();
    }

    @GetMapping("subject")
    public Subject getSubjectById(@Valid @RequestBody SearchByIdDto dto) {
        return subjectService.getSubjectById(dto.id(), false);
    }

    @PostMapping("subject")
    @ResponseStatus(HttpStatus.CREATED)
    public Subject createSubject(@Valid @RequestBody SubjectDto dto) {
        return subjectService.createSubject(dto);
    }

    @PutMapping("subject")
    public Subject updateSubject(@Valid @RequestBody SubjectDto dto) {
        return subjectService.updateSubject(String.valueOf(dto.id()), dto);
    }

    @DeleteMapping("subject")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSubject(@Valid @RequestBody SearchByIdDto dto) {
        subjectService.deleteSubject(dto.id());
    }
}
