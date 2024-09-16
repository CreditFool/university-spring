package com.creditfool.university_spring.service.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.creditfool.university_spring.entity.Teacher;
import com.creditfool.university_spring.repository.TeacherRepository;
import com.creditfool.university_spring.service.TeacherService;
import com.creditfool.university_spring.util.RepositoryValidator;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TeacherServiceImpl extends CommonCrudServiceImpl<Teacher> implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final RepositoryValidator<Teacher> teacherRepositoryValidator;

    @Override
    public TeacherRepository getRepository() {
        return this.teacherRepository;
    }

    @Override
    public RepositoryValidator<Teacher> getRepositoryValidator() {
        return this.teacherRepositoryValidator;
    }

    @Override
    public Teacher updatePartial(UUID id, Teacher updatedData) {
        Teacher currentData = getById(id, true);
        repositoryValidator.validate(updatedData);
        if (updatedData.getFirstName() != null) {
            currentData.setFirstName(updatedData.getFirstName());
        }
        if (updatedData.getLastName() != null) {
            currentData.setLastName(updatedData.getLastName());
        }
        if (updatedData.getAddress() != null) {
            currentData.setAddress(updatedData.getAddress());
        }
        if (updatedData.getEmail() != null) {
            currentData.setEmail(updatedData.getEmail());
        }
        if (updatedData.getPhone() != null) {
            currentData.setPhone(updatedData.getPhone());
        }
        if (updatedData.getNip() != null) {
            currentData.setNip(updatedData.getNip());
        }
        currentData.setUpdatedAt(LocalDateTime.now());
        return repository.save(currentData);
    }
}
