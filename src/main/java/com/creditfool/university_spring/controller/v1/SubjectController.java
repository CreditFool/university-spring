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
import com.creditfool.university_spring.dto.request.SubjectCreateUpdateRequest;
import com.creditfool.university_spring.entity.Subject;
import com.creditfool.university_spring.service.SubjectService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(PathApi.API_V1)
@AllArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @GetMapping(PathApi.SUBJECTS)
    public List<Subject> getAllSubject() {
        return subjectService.getAll();
    }

    @GetMapping(PathApi.SUBJECT)
    public Subject getSubjectById(@Valid @RequestBody SearchByIdRequest request) {
        return subjectService.getById(request.toUUID());
    }

    @PostMapping(PathApi.SUBJECT)
    @ResponseStatus(HttpStatus.CREATED)
    public Subject createSubject(@Valid @RequestBody SubjectCreateUpdateRequest request) {
        return subjectService.create(request.toSubject());
    }

    @PutMapping(PathApi.SUBJECT)
    public Subject updateSubject(@Valid @RequestBody SubjectCreateUpdateRequest request) {
        Subject requestEntity = request.toSubject();
        return subjectService.update(requestEntity.getId(), requestEntity);
    }

    @DeleteMapping(PathApi.SUBJECT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSubject(@Valid @RequestBody SearchByIdRequest request) {
        subjectService.delete(request.toUUID());
    }
}
