package com.creditfool.university_spring.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.creditfool.university_spring.dto.response.TeacherListResponse;
import com.creditfool.university_spring.entity.Teacher;

public interface TeacherRepository extends CommonMethodRepository<Teacher> {

    @Query("SELECT t FROM Teacher t WHERE (t.email = :email OR t.phone = :phone OR t.nip = :nip) AND t.deletedAt is null")
    List<Teacher> findAllByEmailIgnoreCaseOrPhoneOrNipAndDeletedAtIsNull(String email, String phone, String nip);

    Page<TeacherListResponse> findAllListByDeletedAtIsNull(Pageable pageable);

    Page<TeacherListResponse> findAllListByDeletedAtIsNotNull(Pageable pageable);
}
