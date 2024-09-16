package com.creditfool.university_spring.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.creditfool.university_spring.dto.response.StudentListResponse;
import com.creditfool.university_spring.entity.Student;

@Service
public interface StudentService extends CommonCrudService<Student, StudentListResponse> {

    Student updatePartial(UUID id, Student updatedData);
}
