package com.creditfool.university_spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.creditfool.university_spring.entity.Teacher;

public interface TeacherRepository extends CommonMethodRepository<Teacher, Teacher> {

    @Query("SELECT t FROM Teacher t WHERE (t.email = :email OR t.phone = :phone OR t.nip = :nip) AND t.deletedAt is null")
    List<Teacher> findAllByEmailIgnoreCaseOrPhoneOrNipAndDeletedAtIsNull(String email, String phone, String nip);
}
