package com.creditfool.university_spring.service.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.creditfool.university_spring.dto.response.StudentListResponse;
import com.creditfool.university_spring.entity.Student;
import com.creditfool.university_spring.repository.StudentRepository;
import com.creditfool.university_spring.service.StudentService;
import com.creditfool.university_spring.util.RepositoryValidator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl extends CommonCrudServiceImpl<Student, StudentListResponse> implements StudentService {

    private final StudentRepository studentRepository;
    private final RepositoryValidator<Student> studentRepositoryValidator;

    @Override
    public StudentRepository getRepository() {
        return this.studentRepository;
    }

    @Override
    public RepositoryValidator<Student> getRepositoryValidator() {
        return this.studentRepositoryValidator;
    }

    @Override
    public Student updatePartial(UUID id, Student updatedData) {
        Student currentData = getById(id, true);
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
        if (updatedData.getNim() != null) {
            currentData.setNim(updatedData.getNim());
        }
        currentData.setUpdatedAt(LocalDateTime.now());
        return repository.save(currentData);
    }
}
