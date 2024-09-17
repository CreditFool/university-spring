package com.creditfool.university_spring.service;

import org.springframework.data.domain.Page;

import com.creditfool.university_spring.dto.response.SubjectResponse;
import com.creditfool.university_spring.entity.Subject;

public interface SubjectService extends CommonCrudService<Subject> {

    Page<SubjectResponse> getAllForListResponse(int page, int size);

    Page<SubjectResponse> getAllForListResponse(int page, int size, boolean isActive);
}
