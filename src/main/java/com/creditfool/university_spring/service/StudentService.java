package com.creditfool.university_spring.service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.creditfool.university_spring.dto.StudentDto;
import com.creditfool.university_spring.entity.Student;

@Service
public interface StudentService {
    public List<Student> getAllStudent();

    public List<Student> getAllStudent(boolean isActive);

    public Student getStudentById(String id, boolean getNotActive);

    public Student getStudentByNim(String nim);

    public Student createStudent(StudentDto teacherDto);

    public Student updateStudent(String id, StudentDto updatedStudent);

    public void deleteStudent(String id);

    public void deleteStudent(String id, boolean isHardDelete);
}
