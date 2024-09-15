package com.creditfool.university_spring.repository;

import java.util.List;

import com.creditfool.university_spring.entity.Student;

public interface StudentRepository extends CommonMethodRepository<Student> {
    List<Student> findAllByEmailIgnoreCaseOrPhoneOrNimAndDeletedAtIsNull(String email, String phone, String nim);

}
