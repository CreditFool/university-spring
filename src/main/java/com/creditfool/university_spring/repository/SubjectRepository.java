package com.creditfool.university_spring.repository;

import java.util.Optional;

import com.creditfool.university_spring.entity.Subject;

public interface SubjectRepository extends CommonMethodRepository<Subject> {
    Optional<Subject> findByNameIgnoreCaseAndDeletedAtIsNull(String name);
}
