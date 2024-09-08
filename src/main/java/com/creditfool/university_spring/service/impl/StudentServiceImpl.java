package com.creditfool.university_spring.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.creditfool.university_spring.dto.StudentDto;
import com.creditfool.university_spring.entity.Student;
import com.creditfool.university_spring.exception.DataAlreadyExistException;
import com.creditfool.university_spring.exception.DataNotFoundException;
import com.creditfool.university_spring.repository.StudentRepository;
import com.creditfool.university_spring.service.StudentService;
import com.creditfool.university_spring.util.UUIDValidator;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;

    private final DataNotFoundException studentNotFoundException = new DataNotFoundException("Student not found");

    @Override
    public Student createStudent(StudentDto studentDto) {
        validateStudentData(studentDto);
        Student student = dtoToEntity(studentDto);
        return repository.save(student);
    }

    @Override
    public void deleteStudent(String id) {
        Student student = getStudentById(id, false);
        student.setIsActive(false);
        repository.save(student);
    }

    @Override
    public void deleteStudent(String id, boolean isHardDelete) {
        if (isHardDelete) {
            Student student = getStudentById(id, true);
            repository.delete(student);

        } else {
            deleteStudent(id);
        }

    }

    @Override
    public List<Student> getAllStudent() {
        return repository.findAll();
    }

    @Override
    public List<Student> getAllStudent(boolean isActive) {
        return repository.findByIsActive(isActive);
    }

    @Override
    public Student getStudentById(String id, boolean getNotActive) {
        if (!UUIDValidator.isValid(id)) {
            throw studentNotFoundException;
        }
        Optional<Student> student = repository.findById(UUID.fromString(id));
        if (!student.isPresent()) {
            throw studentNotFoundException;
        }
        if (!student.get().getIsActive().booleanValue() && !getNotActive) {
            throw studentNotFoundException;
        }
        return student.get();
    }

    @Override
    public Student getStudentByNim(String nim) {
        Optional<Student> student = repository.findByNim(nim);
        if (!student.isPresent()) {
            throw studentNotFoundException;
        }
        return student.get();
    }

    @Override
    public Student updateStudent(String id, StudentDto updatedStudent) {
        Student student = getStudentById(id, false);
        validateStudentData(updatedStudent);
        if (updatedStudent.firstName() != null) {
            student.setFirstName(updatedStudent.firstName());
        }
        if (updatedStudent.lastName() != null) {
            student.setLastName(updatedStudent.lastName());
        }
        if (updatedStudent.nim() != null) {
            student.setNim(updatedStudent.nim());
        }
        if (updatedStudent.address() != null) {
            student.setAddress(updatedStudent.address());
        }
        if (updatedStudent.phone() != null) {
            student.setPhone(updatedStudent.phone());
        }
        if (updatedStudent.email() != null) {
            student.setEmail(updatedStudent.email());
        }
        repository.save(student);
        return student;
    }

    private void validateStudentData(StudentDto studentDto) {
        List<Student> activeStudent = getAllStudent(true);
        for (Student student : activeStudent) {
            if (student.getId().equals(studentDto.id())) {
                continue;
            }
            if (student.getNim().equals(studentDto.nim())) {
                throw new DataAlreadyExistException("Student with same nim already exist");
            }
            if (student.getEmail().equals(studentDto.email())) {
                throw new DataAlreadyExistException("Student with same Email already exist");
            }
            if (student.getPhone().equals(studentDto.phone())) {
                throw new DataAlreadyExistException("Student with same Phone Number already exist");
            }
        }
    }

    private Student dtoToEntity(StudentDto studentDto) {
        Student student = new Student();
        student.setFirstName(studentDto.firstName());
        student.setLastName(studentDto.lastName());
        student.setNim(studentDto.nim());
        student.setAddress(studentDto.address());
        student.setEmail(studentDto.email());
        student.setPhone(studentDto.phone());
        return student;
    }
}
