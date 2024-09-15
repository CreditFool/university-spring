package com.creditfool.university_spring.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.creditfool.university_spring.entity.Student;

public interface StudentRepository extends JpaRepository<Student, UUID> {

    List<Student> findAllByDeletedAtIsNull();

    List<Student> findAllByDeletedAtIsNotNull();

    Page<Student> findAllByDeletedAtIsNull(Pageable pageable);

    Page<Student> findAllByDeletedAtIsNotNull(Pageable pageable);

    Student findByIdAndDeletedAtIsNull(UUID id);

    List<Student> findAllByEmailIgnoreCaseOrPhoneOrNimAndDeletedAtIsNull(String email, String phone, String nim);

}
