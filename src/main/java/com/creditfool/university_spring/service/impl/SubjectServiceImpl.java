package com.creditfool.university_spring.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.creditfool.university_spring.dto.response.SubjectResponse;
import com.creditfool.university_spring.entity.Subject;
import com.creditfool.university_spring.repository.SubjectRepository;
import com.creditfool.university_spring.service.SubjectService;
import com.creditfool.university_spring.util.RepositoryValidator;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SubjectServiceImpl extends CommonCrudServiceImpl<Subject> implements SubjectService {

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

    @Override
    public Page<SubjectResponse> getAllForListResponse(int page, int size) {
        return getRepository().findAllListByDeletedAtIsNull(PageRequest.of(page - 1, size));
    }

    @Override
    public Page<SubjectResponse> getAllForListResponse(int page, int size, boolean isActive) {
        if (isActive) {
            return getAllForListResponse(page, size);
        }
        return getRepository().findAllListByDeletedAtIsNotNull(PageRequest.of(page - 1, size));
    }
}
