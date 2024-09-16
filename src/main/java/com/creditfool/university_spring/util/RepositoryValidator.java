package com.creditfool.university_spring.util;

import java.util.List;

import org.springframework.stereotype.Component;

import com.creditfool.university_spring.entity.Student;
import com.creditfool.university_spring.exception.DataAlreadyExistException;
import com.creditfool.university_spring.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RepositoryValidator<T> {
    private final StudentRepository studentRepository;

    public void validate(T data) {
        if (data instanceof Student) {
            validateStudent((Student) data);
        }
    }

    public void validateStudent(Student student) {
        List<Student> listData = studentRepository.findAllByEmailIgnoreCaseOrPhoneOrNimAndDeletedAtIsNull(
                student.getEmail(), student.getPhone(), student.getNim());

        for (Student data : listData) {
            if (data.getId().equals(student.getId())) {
                continue;
            }
            if (data.getEmail().equals(student.getEmail())) {
                throw new DataAlreadyExistException("Email already used");
            }
            if (data.getPhone().equals(student.getPhone())) {
                throw new DataAlreadyExistException("Phone Number already used");
            }
            if (data.getNim().equals(student.getNim())) {
                throw new DataAlreadyExistException("NIM already used");
            }
        }
    }
}
