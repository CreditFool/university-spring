package com.creditfool.university_spring.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.creditfool.university_spring.dto.response.TeacherListResponse;
import com.creditfool.university_spring.entity.Teacher;

@Service
public interface TeacherService extends CommonCrudService<Teacher> {
    Teacher updatePartial(UUID id, Teacher updatedData);

    Page<TeacherListResponse> getAllForListResponse(int page, int size);

    Page<TeacherListResponse> getAllForListResponse(int page, int size, boolean isActive);
}
