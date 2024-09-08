package com.creditfool.university_spring.repository;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.creditfool.university_spring.entity.Student;

public interface StudentRepository extends JpaRepository<Student, UUID> {

    List<Student> findByIsActive(Boolean isActive);

    Student findByIdAndIsActive(UUID id, Boolean isActive);

    @Query("SELECT s FROM Student s WHERE s.nim = :nim AND s.isActive = true")
    Optional<Student> findByNim(String nim);
}
