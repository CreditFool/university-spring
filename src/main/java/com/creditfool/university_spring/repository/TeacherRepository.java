package com.creditfool.university_spring.repository;

import java.util.List;

import com.creditfool.university_spring.entity.Teacher;

public interface TeacherRepository extends CommonMethodRepository<Teacher> {
    List<Teacher> findAllByEmailIgnoreCaseOrPhoneOrNipAndDeletedAtIsNull(String email, String phone, String nip);
}
