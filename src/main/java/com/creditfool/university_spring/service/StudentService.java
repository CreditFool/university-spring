package com.creditfool.university_spring.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.creditfool.university_spring.dto.response.StudentListResponse;
import com.creditfool.university_spring.entity.Student;

@Service
public interface StudentService extends CommonCrudService<Student> {

    Student updatePartial(UUID id, Student updatedData);

    Page<StudentListResponse> getAllForListResponse(int page, int size);

    Page<StudentListResponse> getAllForListResponse(int page, int size, boolean isActive);
}
