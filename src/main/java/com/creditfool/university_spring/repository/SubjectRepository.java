package com.creditfool.university_spring.repository;

import com.creditfool.university_spring.entity.Subject;

public interface SubjectRepository extends CommonMethodRepository<Subject> {
    Subject findAllByNameIgnoreCaseAndDeletedAtIsNull(String name);
}
