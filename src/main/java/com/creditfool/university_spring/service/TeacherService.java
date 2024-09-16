package com.creditfool.university_spring.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.creditfool.university_spring.entity.Teacher;

@Service
public interface TeacherService extends CommonCrudService<Teacher, Teacher> {
    Teacher updatePartial(UUID id, Teacher updatedData);
}
