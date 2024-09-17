package com.creditfool.university_spring.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.creditfool.university_spring.dto.response.SubjectResponse;
import com.creditfool.university_spring.entity.Subject;

public interface SubjectRepository extends CommonMethodRepository<Subject> {
    Optional<Subject> findByNameIgnoreCaseAndDeletedAtIsNull(String name);

    Page<SubjectResponse> findAllListByDeletedAtIsNull(Pageable pageable);

    Page<SubjectResponse> findAllListByDeletedAtIsNotNull(Pageable pageable);
}
