package com.creditfool.university_spring.service.impl;

import org.springframework.stereotype.Service;

import com.creditfool.university_spring.entity.Subject;
import com.creditfool.university_spring.repository.SubjectRepository;
import com.creditfool.university_spring.service.SubjectService;
import com.creditfool.university_spring.util.RepositoryValidator;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SubjectServiceImpl extends CommonCrudServiceImpl<Subject, Subject> implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final RepositoryValidator<Subject> subjectRepositoryValidator;

    @Override
    public SubjectRepository getRepository() {
        return this.subjectRepository;
    }

    @Override
    public RepositoryValidator<Subject> getRepositoryValidator() {
        return this.subjectRepositoryValidator;
    }
}
