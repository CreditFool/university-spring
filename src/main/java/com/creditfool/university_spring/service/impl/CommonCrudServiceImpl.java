package com.creditfool.university_spring.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.creditfool.university_spring.entity.Profile;
import com.creditfool.university_spring.exception.DataNotFoundException;
import com.creditfool.university_spring.repository.CommonMethodRepository;
import com.creditfool.university_spring.service.CommonCrudService;
import com.creditfool.university_spring.util.RepositoryValidator;

abstract class CommonCrudServiceImpl<T, R> implements CommonCrudService<T, R> {

    protected CommonMethodRepository<T, R> repository;
    protected RepositoryValidator<T> repositoryValidator;

    protected CommonMethodRepository<T, R> getRepository() {
        return this.repository;
    }

    protected RepositoryValidator<T> getRepositoryValidator() {
        return this.repositoryValidator;
    }

    @Override
    public T create(T data) {
        getRepositoryValidator().validate(data);
        return getRepository().save(data);
    }

    @Override
    public void delete(UUID id) {
        T data = getById(id, true);
        ((Profile) data).setDeletedAt(LocalDateTime.now());
        getRepository().save(data);
    }

    public void delete(UUID id, boolean isSoftDelete) {
        if (isSoftDelete) {
            delete(id);
        } else {
            getRepository().deleteById(id);
        }
    }

    @Override
    public List<T> getAll() {
        return getRepository().findAll();
    }

    @Override
    public List<T> getAll(boolean isActive) {
        if (isActive) {
            return getRepository().findAllByDeletedAtIsNull();
        }
        return getRepository().findAllByDeletedAtIsNotNull();
    }

    @Override
    public Page<R> getAll(int page, int size) {
        return getRepository().findListAll(PageRequest.of(page - 1, size));
    }

    @Override
    public Page<R> getAll(boolean isActive, int page, int size) {
        if (isActive) {
            return getRepository().findAllByDeletedAtIsNull(PageRequest.of(page - 1, size));
        }
        return getRepository().findAllByDeletedAtIsNotNull(PageRequest.of(page - 1, size));
    }

    @Override
    public T getById(UUID id) {
        return getById(id, false);
    }

    @Override
    public T getById(UUID id, boolean mustActive) {
        if (mustActive) {
            return getRepository().findByIdAndDeletedAtIsNull(id)
                    .orElseThrow(() -> new DataNotFoundException("Data not found"));
        }
        return getRepository().findById(id).orElseThrow(() -> new DataNotFoundException("Data not found"));
    }

    @Override
    public T update(UUID id, T updatedData) {
        T currentData = getById(id, true);
        getRepositoryValidator().validate(updatedData);
        ((Profile) updatedData).setCreatedAt(((Profile) currentData).getCreatedAt());
        ((Profile) updatedData).setUpdatedAt(LocalDateTime.now());
        return getRepository().save(updatedData);
    }
}
