package com.creditfool.university_spring.util;

import java.util.List;

import org.springframework.stereotype.Component;

import com.creditfool.university_spring.entity.Student;
import com.creditfool.university_spring.entity.Teacher;
import com.creditfool.university_spring.exception.DataAlreadyExistException;
import com.creditfool.university_spring.repository.StudentRepository;
import com.creditfool.university_spring.repository.TeacherRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RepositoryValidator<T> {
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public void validate(T data) {
        if (data instanceof Student) {
            validateStudent((Student) data);

        } else if (data instanceof Teacher) {
            validateTeacher((Teacher) data);

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

    public void validateTeacher(Teacher teacher) {
        List<Teacher> listData = teacherRepository.findAllByEmailIgnoreCaseOrPhoneOrNipAndDeletedAtIsNull(
                teacher.getEmail(), teacher.getPhone(), teacher.getNip());

        for (Teacher data : listData) {
            if (data.getId().equals(teacher.getId())) {
                continue;
            }
            if (data.getEmail().equals(teacher.getEmail())) {
                throw new DataAlreadyExistException("Email already used");
            }
            if (data.getPhone().equals(teacher.getPhone())) {
                throw new DataAlreadyExistException("Phone Number already used");
            }
            if (data.getNip().equals(teacher.getNip())) {
                throw new DataAlreadyExistException("NIP already used");
            }
        }
    }
}
