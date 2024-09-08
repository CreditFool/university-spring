package com.creditfool.university_spring.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.creditfool.university_spring.dto.TeacherDto;
import com.creditfool.university_spring.entity.Teacher;
import com.creditfool.university_spring.exception.DataAlreadyExistException;
import com.creditfool.university_spring.exception.DataNotFoundException;
import com.creditfool.university_spring.service.TeacherService;

@SpringBootTest
class TeacherServiceImplIntegrationTest {

    @Autowired
    private TeacherService service;

    @BeforeEach
    void setup() {
        TeacherDto newTeacher = new TeacherDto(null, "Mariela", "Lakin",
                "9994567890", "5698 Bednar Spurs", "8883026529", "mari@mail.com");
        service.createTeacher(newTeacher);

        newTeacher = new TeacherDto(null, "Ashley", "Kutch",
                "9994567891", "5698 Bednar Spurs", "8874026510", "kutch@mail.com");
        service.createTeacher(newTeacher);
    }

    @AfterEach
    void clear() {
        List<Teacher> savedList = service.getAllTeacher();
        for (Teacher teacher : savedList) {
            service.deleteTeacher(String.valueOf(teacher.getId()), true);
        }

    }

    @Test
    void testGetAllTeacher() {
        List<Teacher> actualList = service.getAllTeacher();
        assertEquals(2, actualList.size());

        actualList = service.getAllTeacher(true);
        assertEquals(2, actualList.size());

        actualList = service.getAllTeacher(false);
        assertEquals(0, actualList.size());

    }

    @Test
    void testCreateTeacher() {
        TeacherDto newTeacher = new TeacherDto(null, "Filiberto", "Runolfsdottir",
                "1234567890", "55498 Oral Ferry", "827-346-0378", "fili@mail.com");
        service.createTeacher(newTeacher);

        List<Teacher> actualList = service.getAllTeacher();
        assertEquals(3, actualList.size());

        newTeacher = new TeacherDto(null, "Lea", "Runolfsdottir",
                "1234567891", "55498 Oral Ferry", "827-346-03700", "lea@mail.com");
        service.createTeacher(newTeacher);
        actualList = service.getAllTeacher();
        assertEquals(4, actualList.size());

        TeacherDto notValidTeacher = new TeacherDto(null, "Berto", "Runo",
                "1234567890", "55498 Oral Ferry", "82713460378", "berto@mail.com");
        try {
            service.createTeacher(notValidTeacher);

        } catch (RuntimeException e) {
            assertEquals(DataAlreadyExistException.class, e.getClass());
            assertEquals("Teacher with same NIP already exist", e.getMessage(),
                    "Must fail because same NIP for active teacher found");

        }

        try {
            notValidTeacher = new TeacherDto(null, "Berto", "Runo",
                    "1234567999", "55498 Oral Ferry", "827-346-0378", "berto@mail.com");
            service.createTeacher(notValidTeacher);

        } catch (RuntimeException e) {
            assertEquals(DataAlreadyExistException.class, e.getClass());
            assertEquals("Teacher with same Phone Number already exist", e.getMessage(),
                    "Must fail because same Phone Number for active teacher found");

        }

        try {
            notValidTeacher = new TeacherDto(null, "Berto", "Runo",
                    "1234567999", "55498 Oral Ferry", "927-346-0378", "fili@mail.com");
            service.createTeacher(notValidTeacher);

        } catch (RuntimeException e) {
            assertEquals(DataAlreadyExistException.class, e.getClass());
            assertEquals("Teacher with same Email already exist", e.getMessage(),
                    "Must fail because same Email for active teacher found");

        }
        actualList = service.getAllTeacher();
        assertEquals(4, actualList.size(), "Must keep number of all teacher and fail attemp not saved");

    }

    @Test
    void testGetTeacherById() {
        List<Teacher> actualList = service.getAllTeacher();
        for (Teacher teacher : actualList) {
            assertEquals(teacher, service.getTeacherById(String.valueOf(teacher.getId())));
        }

        try {
            Teacher invalidTeacher = service.getTeacherById("123456");
            assertNull(invalidTeacher);

        } catch (RuntimeException e) {
            assertEquals(DataNotFoundException.class, e.getClass());
            assertEquals("Teacher not found", e.getMessage(), "Must fail because teacher found");

        }
    }

    @Test
    void testGetTeacherByNip() {
        String nip = "1234567890";
        String firstName = "Filiberto";
        String email = "fili@mail.com";

        TeacherDto newTeacher = new TeacherDto(null, firstName, "Runolfsdottir",
                nip, "55498 Oral Ferry", "827-346-0378", email);
        service.createTeacher(newTeacher);

        Teacher savedTeacher = service.getTeacherByNip(nip);
        assertNotNull(savedTeacher);
        assertEquals(nip, savedTeacher.getNip());
        assertEquals(firstName, savedTeacher.getFirstName());
        assertEquals(email, savedTeacher.getEmail());

        try {
            Teacher invalidTeacher = service.getTeacherByNip("923456");
            assertNull(invalidTeacher);

        } catch (RuntimeException e) {
            assertEquals(DataNotFoundException.class, e.getClass());
            assertEquals("Teacher not found", e.getMessage(), "Must fail because teacher found");

        }
    }

    @Test
    void testUpdateTeacher() {
        String usedNip = "9994567890";
        String usedEmail = "mari@mail.com";
        String usedPhone = "8883026529";

        String nip = "1234567";
        String oldFirstName = "Rivaldo";
        String newFirstName = "Adolf";

        String oldEmail = "riva@mail.com";
        String newEmail = "new@mail.com";

        TeacherDto newTeacher = new TeacherDto(null, oldFirstName, "Runolfsdottir",
                nip, "55498 Oral Ferry", "8273460378", "riva@mail.com");
        service.createTeacher(newTeacher);
        List<Teacher> actualList = service.getAllTeacher();
        assertEquals(3, actualList.size());

        Teacher savedTeacher = service.getTeacherByNip(nip);
        assertEquals(oldFirstName, savedTeacher.getFirstName());
        assertEquals(oldEmail, savedTeacher.getEmail());

        service.updateTeacher(String.valueOf(savedTeacher.getId()),
                new TeacherDto(null, newFirstName, null, null, null, null, null));

        Teacher updatedTeacher = service.getTeacherById(String.valueOf(savedTeacher.getId()));
        assertEquals(newFirstName, updatedTeacher.getFirstName());
        assertEquals(oldEmail, updatedTeacher.getEmail());

        TeacherDto notValidTeacher;
        try {
            notValidTeacher = new TeacherDto(null, null, null,
                    null, null, usedPhone, null);
            assertNull(service.updateTeacher(String.valueOf(savedTeacher.getId()), notValidTeacher));

        } catch (RuntimeException e) {
            assertEquals(DataAlreadyExistException.class, e.getClass());
            assertEquals("Teacher with same Phone Number already exist", e.getMessage(),
                    "Must fail because same Phone Number for active teacher found");

        }

        try {
            notValidTeacher = new TeacherDto(null, null, null,
                    usedNip, null, null, null);
            assertNull(service.updateTeacher(String.valueOf(savedTeacher.getId()), notValidTeacher));

        } catch (RuntimeException e) {
            assertEquals(DataAlreadyExistException.class, e.getClass());
            assertEquals("Teacher with same NIP already exist", e.getMessage(),
                    "Must fail because same NIP for active teacher found");

        }

        try {
            notValidTeacher = new TeacherDto(null, null, null,
                    null, null, null, usedEmail);
            assertNull(service.updateTeacher(String.valueOf(savedTeacher.getId()), notValidTeacher));

        } catch (RuntimeException e) {
            assertEquals(DataAlreadyExistException.class, e.getClass());
            assertEquals("Teacher with same Email already exist", e.getMessage(),
                    "Must fail because same Email for active teacher found");

        }

        Teacher sameTeacher = service.getTeacherById(String.valueOf(savedTeacher.getId()));
        assertEquals(updatedTeacher.getEmail(), sameTeacher.getEmail());
        assertEquals(updatedTeacher.getNip(), sameTeacher.getNip());
        assertEquals(updatedTeacher.getPhone(), sameTeacher.getPhone());

        service.updateTeacher(String.valueOf(savedTeacher.getId()),
                new TeacherDto(null, null, null, null, null, null, newEmail));

        updatedTeacher = service.getTeacherById(String.valueOf(savedTeacher.getId()));
        assertEquals(newEmail, updatedTeacher.getEmail());
    }

    @Test
    void testDeleteTeacher() {
        String nip = "1234567890";
        TeacherDto newTeacher = new TeacherDto(null, "Filiberto", "Runolfsdottir",
                nip, "55498 Oral Ferry", "827-346-0378", "fili@mail.com");
        Teacher savedTeacher = service.createTeacher(newTeacher);
        assertTrue(savedTeacher.getIsActive());
        assertEquals(newTeacher.firstName(), service.getTeacherByNip(nip).getFirstName());

        List<Teacher> allTeachers = service.getAllTeacher();
        List<Teacher> activeTeachers = service.getAllTeacher(true);
        List<Teacher> notActiveTeachers = service.getAllTeacher(false);
        assertEquals(3, allTeachers.size());
        assertEquals(3, activeTeachers.size());
        assertEquals(0, notActiveTeachers.size());

        TeacherDto teacherWithSameNip = new TeacherDto(null, "Abby", "Dottir",
                nip, "55498 Oral Ferry", "820-346-0378", "abby@mail.com");
        try {
            service.createTeacher(teacherWithSameNip);

        } catch (RuntimeException e) {
            assertEquals(DataAlreadyExistException.class, e.getClass());
            assertEquals("Teacher with same NIP already exist", e.getMessage(),
                    "Must fail because same NIP for active teacher found");

        }

        service.deleteTeacher(String.valueOf(savedTeacher.getId()));
        Teacher deletedTeacher = service.getTeacherById(String.valueOf(savedTeacher.getId()));
        assertFalse(deletedTeacher.getIsActive());

        try {
            Teacher invalidTeacher = service.getTeacherByNip(nip);
            assertNull(invalidTeacher);

        } catch (RuntimeException e) {
            assertEquals(DataNotFoundException.class, e.getClass());
            assertEquals("Teacher not found", e.getMessage(), "Must fail because teacher with that nip not active");

        }

        allTeachers = service.getAllTeacher();
        activeTeachers = service.getAllTeacher(true);
        notActiveTeachers = service.getAllTeacher(false);
        assertEquals(3, allTeachers.size());
        assertEquals(2, activeTeachers.size());
        assertEquals(1, notActiveTeachers.size());

        service.createTeacher(teacherWithSameNip);
        assertNotNull(service.getTeacherByNip(nip));
        assertEquals(teacherWithSameNip.firstName(), service.getTeacherByNip(nip).getFirstName());

        allTeachers = service.getAllTeacher();
        activeTeachers = service.getAllTeacher(true);
        notActiveTeachers = service.getAllTeacher(false);
        assertEquals(4, allTeachers.size());
        assertEquals(3, activeTeachers.size());
        assertEquals(1, notActiveTeachers.size());

        service.deleteTeacher(String.valueOf(savedTeacher.getId()), true);
        allTeachers = service.getAllTeacher();
        activeTeachers = service.getAllTeacher(true);
        notActiveTeachers = service.getAllTeacher(false);
        assertEquals(3, allTeachers.size());
        assertEquals(3, activeTeachers.size());
        assertEquals(0, notActiveTeachers.size());

        try {
            Teacher invalidTeacher = service.getTeacherById(String.valueOf(savedTeacher.getId()));
            assertNull(invalidTeacher);

        } catch (RuntimeException e) {
            assertEquals(DataNotFoundException.class, e.getClass());
            assertEquals("Teacher not found", e.getMessage(),
                    "Must fail because teacher with that id already hard deleted");

        }
    }

}
