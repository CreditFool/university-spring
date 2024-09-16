package com.creditfool.university_spring.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;

public interface CommonCrudService<T, R> {
    List<T> getAll();

    List<T> getAll(boolean isActive);

    Page<R> getAll(int page, int size);

    Page<R> getAll(boolean isActive, int page, int size);

    T getById(UUID id);

    T getById(UUID id, boolean mustActive);

    T create(T data);

    T update(UUID id, T updatedData);

    void delete(UUID id);

    void delete(UUID id, boolean isSoftDelete);
}
