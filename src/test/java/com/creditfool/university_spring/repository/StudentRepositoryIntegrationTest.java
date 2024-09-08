package com.creditfool.university_spring.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.creditfool.university_spring.entity.Student;

@SpringBootTest
class StudentRepositoryIntegrationTest {

    @Autowired
    private StudentRepository repository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private List<Student> baseData = new ArrayList<>(List.of(
            new Student(null, "Berenice", "Cartwright", "1234567890", "7584 Marquardt Isle",
                    "081234567890", "emailA@mail.zzz", true),
            new Student(null, "Katheryn", "Hills", "1234567891", "Cronin Coves",
                    "081234567891", "emailB@mail.zzz", true),
            new Student(null, "Marcia", "O'Kon", "1234567892", "Wiza Rapid",
                    "081234567892", "emailC@mail.zzz", false)));

    @BeforeEach
    private void setup() {
        repository.saveAll(baseData);
    }

    @AfterEach
    public void execute() {
        jdbcTemplate.execute("TRUNCATE TABLE student");
    }

    @Test
    void testFindAll() {
        List<Student> actualData = repository.findAll();
        assertEquals(3, actualData.size());
    }

    @Test
    void testFindByIsActive() {
        List<Student> actualData = repository.findByIsActive(true);
        assertEquals(2, actualData.size());

        actualData = repository.findByIsActive(false);
        assertEquals(1, actualData.size());
    }

    @Test
    void testFindByIdAndIsActive() {
        List<Student> actualData = repository.findByIsActive(true);
        for (Student actual : actualData) {
            assertEquals(actual, repository.findById(actual.getId()).get());
        }

        actualData = repository.findByIsActive(false);
        for (Student actual : actualData) {
            assertEquals(actual, repository.findById(actual.getId()).get());
        }
    }

    @Test
    void testFindById() {
        List<Student> actualData = repository.findAll();
        for (Student actual : actualData) {
            assertEquals(actual, repository.findById(actual.getId()).get());
        }

        assertFalse(repository.findById(UUID.randomUUID()).isPresent());
    }

    @Test
    void testFindByNim() {
        String activeNim = "1234567890";
        String notActiveNim = "1234567892";
        String notExistNim = "1236669990";

        Optional<Student> savedStudent = repository.findByNim(activeNim);
        assertTrue(savedStudent.isPresent(), "Check if active student found with nim");

        savedStudent = repository.findByNim(notActiveNim);
        assertFalse(savedStudent.isPresent(), "Check if not active student not found with nim");

        savedStudent = repository.findByNim(notExistNim);
        assertFalse(savedStudent.isPresent(), "Check if not exist nim not found");
    }

    @Test
    void testSave() {
        String firstName = "Jonathan";
        String lastName = "Joestar";
        String nim = "1234567893";
        String address = "lorem ipsum";
        String phone = "081234567890";
        String email = "xxx@yyy.zzz";

        assertEquals(3, repository.count());

        Student newStudent = new Student();
        newStudent.setFirstName(firstName);
        newStudent.setLastName(lastName);
        newStudent.setNim(nim);
        newStudent.setAddress(address);
        newStudent.setPhone(phone);
        newStudent.setEmail(email);
        repository.save(newStudent);
        assertEquals(4, repository.count());

        Optional<Student> savedStudent = repository.findByNim(nim);
        assertTrue(savedStudent.isPresent());
        assertEquals(firstName, savedStudent.get().getFirstName());
        assertEquals(nim, savedStudent.get().getNim());
        assertEquals(phone, savedStudent.get().getPhone());
        assertEquals(true, savedStudent.get().getIsActive());

        String newFirstName = "Joseph";
        UUID savedStudentId = savedStudent.get().getId();
        savedStudent.get().setFirstName(newFirstName);
        repository.save(savedStudent.get());

        Optional<Student> updatedStudent = repository.findById(savedStudentId);
        assertTrue(updatedStudent.isPresent());
        assertEquals(savedStudentId, updatedStudent.get().getId());
        assertEquals(newFirstName, updatedStudent.get().getFirstName());
    }

    @Test
    void testDelete() {
        List<Student> actualData = repository.findAll();
        assertEquals(3, actualData.size());

        Student firstStudent = actualData.get(0);
        assertTrue(actualData.contains(firstStudent));

        repository.delete(firstStudent);
        actualData = repository.findAll();
        assertEquals(2, actualData.size());
        assertFalse(actualData.contains(firstStudent));
    }

}
