package com.creditfool.university_spring.repository;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.creditfool.university_spring.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, UUID> {

    List<Teacher> findByIsActive(Boolean isActive);

    Teacher findByIdAndIsActive(UUID id, Boolean isActive);

    @Query("SELECT t FROM Teacher t WHERE t.nip = :nip AND t.isActive = true")
    Optional<Teacher> findByNip(String nip);
}
