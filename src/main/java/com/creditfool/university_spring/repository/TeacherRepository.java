package com.creditfool.university_spring.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.creditfool.university_spring.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, UUID> {

    List<Teacher> findAllByDeletedAtIsNull();

    List<Teacher> findAllByDeletedAtIsNotNull();

    Page<Teacher> findAllByDeletedAtIsNull(Pageable pageable);

    Page<Teacher> findAllByDeletedAtIsNotNull(Pageable pageable);

    Teacher findByIdAndDeletedAtIsNull(UUID id);

    List<Teacher> findAllByEmailIgnoreCaseOrPhoneOrNipAndDeletedAtIsNull(String email, String phone, String nip);
}
