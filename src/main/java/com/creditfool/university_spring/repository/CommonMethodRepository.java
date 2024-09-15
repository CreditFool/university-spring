package com.creditfool.university_spring.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CommonMethodRepository<T> extends JpaRepository<T, UUID> {
    List<T> findAllByDeletedAtIsNull();

    List<T> findAllByDeletedAtIsNotNull();

    Page<T> findAllByDeletedAtIsNull(Pageable pageable);

    Page<T> findAllByDeletedAtIsNotNull(Pageable pageable);

    T findByIdAndDeletedAtIsNull(UUID id);
}
