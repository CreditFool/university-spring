package com.creditfool.university_spring.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.creditfool.university_spring.dto.response.StudentListResponse;
import com.creditfool.university_spring.entity.Student;

public interface StudentRepository extends CommonMethodRepository<Student> {

    @Query("SELECT s FROM Student s WHERE (s.email = :email OR s.phone = :phone OR s.nim = :nim) AND s.deletedAt is null")
    List<Student> findAllByEmailIgnoreCaseOrPhoneOrNimAndDeletedAtIsNull(String email, String phone, String nim);

    Page<StudentListResponse> findAllListByDeletedAtIsNull(Pageable pageable);

    Page<StudentListResponse> findAllListByDeletedAtIsNotNull(Pageable pageable);
}
