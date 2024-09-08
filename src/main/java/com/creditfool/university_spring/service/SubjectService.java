package com.creditfool.university_spring.service;

import java.util.List;

import com.creditfool.university_spring.dto.SubjectDto;
import com.creditfool.university_spring.entity.Subject;

public interface SubjectService {
    public List<Subject> getAllSubject();

    public List<Subject> getAllSubject(boolean isActive);

    public Subject getSubjectById(String id, boolean getNotActive);

    public Subject getSubjectByName(String name);

    public Subject createSubject(SubjectDto teacherDto);

    public Subject updateSubject(String id, SubjectDto updatedSubject);

    public void deleteSubject(String id);

    public void deleteSubject(String id, boolean isHardDelete);
}
