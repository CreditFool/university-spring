package com.creditfool.university_spring.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.creditfool.university_spring.dto.StudentDto;
import com.creditfool.university_spring.entity.Student;
import com.creditfool.university_spring.exception.DataAlreadyExistException;
import com.creditfool.university_spring.exception.DataNotFoundException;
import com.creditfool.university_spring.service.StudentService;

@SpringBootTest
class StudentServiceImplIntegrationTest {

    @Autowired
    private StudentService service;

    @BeforeEach
    void setup() {
        StudentDto newStudent = new StudentDto(null, "Mariela", "Lakin",
                "9994567890", "5698 Bednar Spurs", "8883026529", "mari@mail.com");
        service.createStudent(newStudent);

        newStudent = new StudentDto(null, "Ashley", "Kutch",
                "9994567891", "5698 Bednar Spurs", "8874026510", "kutch@mail.com");
        service.createStudent(newStudent);
    }

    @AfterEach
    void clear() {
        List<Student> savedList = service.getAllStudent();
        for (Student student : savedList) {
            service.deleteStudent(String.valueOf(student.getId()), true);
        }

    }

    @Test
    void testGetAllStudent() {
        List<Student> actualList = service.getAllStudent();
        assertEquals(2, actualList.size());

        actualList = service.getAllStudent(true);
        assertEquals(2, actualList.size());

        actualList = service.getAllStudent(false);
        assertEquals(0, actualList.size());

    }

    @Test
    void testCreateStudent() {
        StudentDto newStudent = new StudentDto(null, "Filiberto", "Runolfsdottir",
                "1234567890", "55498 Oral Ferry", "827-346-0378", "fili@mail.com");
        service.createStudent(newStudent);

        List<Student> actualList = service.getAllStudent();
        assertEquals(3, actualList.size());

        newStudent = new StudentDto(null, "Lea", "Runolfsdottir",
                "1234567891", "55498 Oral Ferry", "827-346-03700", "lea@mail.com");
        service.createStudent(newStudent);
        actualList = service.getAllStudent();
        assertEquals(4, actualList.size());

        StudentDto notValidStudent = new StudentDto(null, "Berto", "Runo",
                "1234567890", "55498 Oral Ferry", "82713460378", "berto@mail.com");
        try {
            service.createStudent(notValidStudent);

        } catch (RuntimeException e) {
            assertEquals(DataAlreadyExistException.class, e.getClass());
            assertEquals("Student with same nim already exist", e.getMessage(),
                    "Must fail because same nim for active student found");

        }

        try {
            notValidStudent = new StudentDto(null, "Berto", "Runo",
                    "1234567999", "55498 Oral Ferry", "827-346-0378", "berto@mail.com");
            service.createStudent(notValidStudent);

        } catch (RuntimeException e) {
            assertEquals(DataAlreadyExistException.class, e.getClass());
            assertEquals("Student with same Phone Number already exist", e.getMessage(),
                    "Must fail because same Phone Number for active student found");

        }

        try {
            notValidStudent = new StudentDto(null, "Berto", "Runo",
                    "1234567999", "55498 Oral Ferry", "927-346-0378", "fili@mail.com");
            service.createStudent(notValidStudent);

        } catch (RuntimeException e) {
            assertEquals(DataAlreadyExistException.class, e.getClass());
            assertEquals("Student with same Email already exist", e.getMessage(),
                    "Must fail because same Email for active student found");

        }
        actualList = service.getAllStudent();
        assertEquals(4, actualList.size(), "Must keep number of all student and fail attemp not saved");

    }

    @Test
    void testGetStudentById() {
        List<Student> actualList = service.getAllStudent();
        for (Student student : actualList) {
            assertEquals(student, service.getStudentById(String.valueOf(student.getId()), true));
        }

        try {
            Student invalidStudent = service.getStudentById("123456", true);
            assertNull(invalidStudent);

        } catch (RuntimeException e) {
            assertEquals(DataNotFoundException.class, e.getClass());
            assertEquals("Student not found", e.getMessage(), "Must fail because student found");

        }
    }

    @Test
    void testGetStudentByNim() {
        String nim = "1234567890";
        String firstName = "Filiberto";
        String email = "fili@mail.com";

        StudentDto newStudent = new StudentDto(null, firstName, "Runolfsdottir",
                nim, "55498 Oral Ferry", "827-346-0378", email);
        service.createStudent(newStudent);

        Student savedStudent = service.getStudentByNim(nim);
        assertNotNull(savedStudent);
        assertEquals(nim, savedStudent.getNim());
        assertEquals(firstName, savedStudent.getFirstName());
        assertEquals(email, savedStudent.getEmail());

        try {
            Student invalidStudent = service.getStudentByNim("923456");
            assertNull(invalidStudent);

        } catch (RuntimeException e) {
            assertEquals(DataNotFoundException.class, e.getClass());
            assertEquals("Student not found", e.getMessage(), "Must fail because student found");

        }
    }

    @Test
    void testUpdateStudent() {
        String usedNim = "9994567890";
        String usedEmail = "mari@mail.com";
        String usedPhone = "8883026529";

        String nim = "1234567";
        String oldFirstName = "Rivaldo";
        String newFirstName = "Adolf";

        String oldEmail = "riva@mail.com";
        String newEmail = "new@mail.com";

        StudentDto newStudent = new StudentDto(null, oldFirstName, "Runolfsdottir",
                nim, "55498 Oral Ferry", "8273460378", "riva@mail.com");
        service.createStudent(newStudent);
        List<Student> actualList = service.getAllStudent();
        assertEquals(3, actualList.size());

        Student savedStudent = service.getStudentByNim(nim);
        assertEquals(oldFirstName, savedStudent.getFirstName());
        assertEquals(oldEmail, savedStudent.getEmail());

        service.updateStudent(String.valueOf(savedStudent.getId()),
                new StudentDto(null, newFirstName, null, null, null, null, null));

        Student updatedStudent = service.getStudentById(String.valueOf(savedStudent.getId()), true);
        assertEquals(newFirstName, updatedStudent.getFirstName());
        assertEquals(oldEmail, updatedStudent.getEmail());

        StudentDto notValidStudent;
        try {
            notValidStudent = new StudentDto(null, null, null,
                    null, null, usedPhone, null);
            assertNull(service.updateStudent(String.valueOf(savedStudent.getId()), notValidStudent));

        } catch (RuntimeException e) {
            assertEquals(DataAlreadyExistException.class, e.getClass());
            assertEquals("Student with same Phone Number already exist", e.getMessage(),
                    "Must fail because same Phone Number for active student found");

        }

        try {
            notValidStudent = new StudentDto(null, null, null,
                    usedNim, null, null, null);
            assertNull(service.updateStudent(String.valueOf(savedStudent.getId()), notValidStudent));

        } catch (RuntimeException e) {
            assertEquals(DataAlreadyExistException.class, e.getClass());
            assertEquals("Student with same nim already exist", e.getMessage(),
                    "Must fail because same nim for active student found");

        }

        try {
            notValidStudent = new StudentDto(null, null, null,
                    null, null, null, usedEmail);
            assertNull(service.updateStudent(String.valueOf(savedStudent.getId()), notValidStudent));

        } catch (RuntimeException e) {
            assertEquals(DataAlreadyExistException.class, e.getClass());
            assertEquals("Student with same Email already exist", e.getMessage(),
                    "Must fail because same Email for active student found");

        }

        Student sameStudent = service.getStudentById(String.valueOf(savedStudent.getId()), true);
        assertEquals(updatedStudent.getEmail(), sameStudent.getEmail());
        assertEquals(updatedStudent.getNim(), sameStudent.getNim());
        assertEquals(updatedStudent.getPhone(), sameStudent.getPhone());

        service.updateStudent(String.valueOf(savedStudent.getId()),
                new StudentDto(null, null, null, null, null, null, newEmail));

        updatedStudent = service.getStudentById(String.valueOf(savedStudent.getId()), true);
        assertEquals(newEmail, updatedStudent.getEmail());
    }

    @Test
    void testDeleteStudent() {
        String nim = "1234567890";
        StudentDto newStudent = new StudentDto(null, "Filiberto", "Runolfsdottir",
                nim, "55498 Oral Ferry", "827-346-0378", "fili@mail.com");
        Student savedStudent = service.createStudent(newStudent);
        assertTrue(savedStudent.getIsActive());
        assertEquals(newStudent.firstName(), service.getStudentByNim(nim).getFirstName());

        List<Student> allStudents = service.getAllStudent();
        List<Student> activeStudents = service.getAllStudent(true);
        List<Student> notActiveStudents = service.getAllStudent(false);
        assertEquals(3, allStudents.size());
        assertEquals(3, activeStudents.size());
        assertEquals(0, notActiveStudents.size());

        StudentDto studentWithSameNim = new StudentDto(null, "Abby", "Dottir",
                nim, "55498 Oral Ferry", "820-346-0378", "abby@mail.com");
        try {
            service.createStudent(studentWithSameNim);

        } catch (RuntimeException e) {
            assertEquals(DataAlreadyExistException.class, e.getClass());
            assertEquals("Student with same nim already exist", e.getMessage(),
                    "Must fail because same nim for active student found");

        }

        service.deleteStudent(String.valueOf(savedStudent.getId()));
        Student deletedStudent = service.getStudentById(String.valueOf(savedStudent.getId()), true);
        assertFalse(deletedStudent.getIsActive());

        try {
            Student invalidStudent = service.getStudentByNim(nim);
            assertNull(invalidStudent);

        } catch (RuntimeException e) {
            assertEquals(DataNotFoundException.class, e.getClass());
            assertEquals("Student not found", e.getMessage(), "Must fail because student with that nim not active");

        }

        allStudents = service.getAllStudent();
        activeStudents = service.getAllStudent(true);
        notActiveStudents = service.getAllStudent(false);
        assertEquals(3, allStudents.size());
        assertEquals(2, activeStudents.size());
        assertEquals(1, notActiveStudents.size());

        service.createStudent(studentWithSameNim);
        assertNotNull(service.getStudentByNim(nim));
        assertEquals(studentWithSameNim.firstName(), service.getStudentByNim(nim).getFirstName());

        allStudents = service.getAllStudent();
        activeStudents = service.getAllStudent(true);
        notActiveStudents = service.getAllStudent(false);
        assertEquals(4, allStudents.size());
        assertEquals(3, activeStudents.size());
        assertEquals(1, notActiveStudents.size());

        service.deleteStudent(String.valueOf(savedStudent.getId()), true);
        allStudents = service.getAllStudent();
        activeStudents = service.getAllStudent(true);
        notActiveStudents = service.getAllStudent(false);
        assertEquals(3, allStudents.size());
        assertEquals(3, activeStudents.size());
        assertEquals(0, notActiveStudents.size());

        try {
            Student invalidStudent = service.getStudentById(String.valueOf(savedStudent.getId()), true);
            assertNull(invalidStudent);

        } catch (RuntimeException e) {
            assertEquals(DataNotFoundException.class, e.getClass());
            assertEquals("Student not found", e.getMessage(),
                    "Must fail because student with that id already hard deleted");

        }
    }

}
