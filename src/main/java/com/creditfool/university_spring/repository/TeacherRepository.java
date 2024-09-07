package com.creditfool.university_spring.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.creditfool.university_spring.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, UUID> {

    List<Teacher> findByIsActive(Boolean isActive);

    Teacher findByIdAndIsActive(UUID id, Boolean isActive);
}
