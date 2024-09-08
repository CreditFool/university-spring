package com.creditfool.university_spring.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.creditfool.university_spring.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, UUID> {
    List<Subject> findByIsActive(Boolean isActive);

    @Query("SELECT s FROM Subject s WHERE s.subjectName ILIKE :name AND s.isActive = true")
    Optional<Subject> findByName(String name);
}