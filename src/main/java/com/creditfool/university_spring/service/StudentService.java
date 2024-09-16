package com.creditfool.university_spring.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.creditfool.university_spring.entity.Student;

@Service
public interface StudentService {

    List<Student> getAll();

    List<Student> getAll(boolean isActive);

    Page<Student> getAll(int page, int size);

    Page<Student> getAll(boolean isActive, int page, int size);

    Student getById(UUID id);

    Student getById(UUID id, boolean mustActive);

    Student create(Student data);

    Student update(UUID id, Student updatedData);

    Student updatePartial(UUID id, Student updatedData);

    void delete(UUID id);

    void delete(UUID id, boolean isSoftDelete);
}
