package com.creditfool.university_spring.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.creditfool.university_spring.entity.Student;
import com.creditfool.university_spring.exception.DataNotFoundException;
import com.creditfool.university_spring.repository.StudentRepository;
import com.creditfool.university_spring.service.StudentService;
import com.creditfool.university_spring.util.RepositoryValidator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final RepositoryValidator repositoryValidator;

    @Override
    public Student create(Student data) {
        repositoryValidator.validate(data);
        return studentRepository.save(data);
    }

    @Override
    public void delete(UUID id) {
        Student studentData = getById(id, true);
        studentData.setDeletedAt(LocalDateTime.now());
        studentRepository.save(studentData);
    }

    public void delete(UUID id, boolean isSoftDelete) {
        if (isSoftDelete) {
            delete(id);
        } else {
            studentRepository.deleteById(id);
        }
    }

    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> getAll(boolean isActive) {
        if (isActive) {
            return studentRepository.findAllByDeletedAtIsNull();
        }
        return studentRepository.findAllByDeletedAtIsNotNull();
    }

    @Override
    public Page<Student> getAll(int page, int size) {
        return studentRepository.findAll(PageRequest.of(page - 1, size));
    }

    @Override
    public Page<Student> getAll(boolean isActive, int page, int size) {
        if (isActive) {
            return studentRepository.findAllByDeletedAtIsNull(PageRequest.of(page - 1, size));
        }
        return studentRepository.findAllByDeletedAtIsNotNull(PageRequest.of(page - 1, size));
    }

    @Override
    public Student getById(UUID id) {
        return getById(id, false);
    }

    @Override
    public Student getById(UUID id, boolean mustActive) {
        if (mustActive) {
            return studentRepository.findByIdAndDeletedAtIsNull(id)
                    .orElseThrow(() -> new DataNotFoundException("Data not found"));
        }
        return studentRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Data not found"));
    }

    @Override
    public Student update(UUID id, Student updatedData) {
        Student currentData = getById(id, true);
        repositoryValidator.validate(updatedData);
        updatedData.setCreatedAt(currentData.getCreatedAt());
        updatedData.setUpdatedAt(LocalDateTime.now());
        return studentRepository.save(updatedData);
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
        return studentRepository.save(currentData);
    }

}
