package com.creditfool.university_spring.service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.creditfool.university_spring.dto.TeacherDto;
import com.creditfool.university_spring.entity.Teacher;

@Service
public interface TeacherService {
    public List<Teacher> getAllTeacher();

    public List<Teacher> getAllTeacher(boolean isActive);

    public Teacher getTeacherById(String id);

    public Teacher getTeacherByNip(String nip);

    public Teacher createTeacher(TeacherDto teacherDto);

    public Teacher updateTeacher(String id, TeacherDto updatedTeacher);

    public void deleteTeacher(String id);

    public void deleteTeacher(String id, boolean isHardDelete);
}
