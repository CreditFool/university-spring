package com.creditfool.university_spring.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.creditfool.university_spring.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, UUID> {

    List<Subject> findAllByDeletedAtIsNull();

    List<Subject> findAllByDeletedAtIsNotNull();

    Page<Subject> findAllByDeletedAtIsNull(Pageable pageable);

    Page<Subject> findAllByDeletedAtIsNotNull(Pageable pageable);

    Subject findByIdAndDeletedAtIsNull(UUID id);

    Subject findAllByNameIgnoreCaseAndDeletedAtIsNull(String name);
}
