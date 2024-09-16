package com.creditfool.university_spring.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CommonMethodRepository<T, R> extends JpaRepository<T, UUID> {
    List<T> findAllByDeletedAtIsNull();

    List<T> findAllByDeletedAtIsNotNull();

    Page<R> findListAll(Pageable pageable);

    Page<R> findAllByDeletedAtIsNull(Pageable pageable);

    Page<R> findAllByDeletedAtIsNotNull(Pageable pageable);

    Optional<T> findByIdAndDeletedAtIsNull(UUID id);
}
