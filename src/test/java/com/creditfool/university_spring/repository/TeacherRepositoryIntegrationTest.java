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

import com.creditfool.university_spring.entity.Teacher;

@SpringBootTest
class TeacherRepositoryIntegrationTest {

    @Autowired
    private TeacherRepository repository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private List<Teacher> baseData = new ArrayList<>(List.of(
            new Teacher(null, "Berenice", "Cartwright", "1234567890", "7584 Marquardt Isle",
                    "081234567890", "emailA@mail.zzz", true),
            new Teacher(null, "Katheryn", "Hills", "1234567891", "Cronin Coves",
                    "081234567891", "emailB@mail.zzz", true),
            new Teacher(null, "Marcia", "O'Kon", "1234567892", "Wiza Rapid",
                    "081234567892", "emailC@mail.zzz", false)));

    @BeforeEach
    private void setup() {
        repository.saveAll(baseData);
    }

    @AfterEach
    public void execute() {
        jdbcTemplate.execute("TRUNCATE TABLE teacher");
    }

    @Test
    void testFindAll() {
        List<Teacher> actualData = repository.findAll();
        assertEquals(3, actualData.size());
    }

    @Test
    void testFindByIsActive() {
        List<Teacher> actualData = repository.findByIsActive(true);
        assertEquals(2, actualData.size());

        actualData = repository.findByIsActive(false);
        assertEquals(1, actualData.size());
    }

    @Test
    void testFindByIdAndIsActive() {
        List<Teacher> actualData = repository.findByIsActive(true);
        for (Teacher actual : actualData) {
            assertEquals(actual, repository.findById(actual.getId()).get());
        }

        actualData = repository.findByIsActive(false);
        for (Teacher actual : actualData) {
            assertEquals(actual, repository.findById(actual.getId()).get());
        }
    }

    @Test
    void testFindById() {
        List<Teacher> actualData = repository.findAll();
        for (Teacher actual : actualData) {
            assertEquals(actual, repository.findById(actual.getId()).get());
        }

        assertFalse(repository.findById(UUID.randomUUID()).isPresent());
    }

    @Test
    void testFindByNip() {
        String activeNip = "1234567890";
        String notActiveNip = "1234567892";
        String notExistNip = "1236669990";

        Optional<Teacher> savedTeacher = repository.findByNip(activeNip);
        assertTrue(savedTeacher.isPresent(), "Check if active teacher found with nip");

        savedTeacher = repository.findByNip(notActiveNip);
        assertFalse(savedTeacher.isPresent(), "Check if not active teacher not found with nip");

        savedTeacher = repository.findByNip(notExistNip);
        assertFalse(savedTeacher.isPresent(), "Check if not exist nip not found");
    }

    @Test
    void testSave() {
        String firstName = "Jonathan";
        String lastName = "Joestar";
        String nip = "1234567893";
        String address = "lorem ipsum";
        String phone = "081234567890";
        String email = "xxx@yyy.zzz";

        assertEquals(3, repository.count());

        Teacher newTeacher = new Teacher();
        newTeacher.setFirstName(firstName);
        newTeacher.setLastName(lastName);
        newTeacher.setNip(nip);
        newTeacher.setAddress(address);
        newTeacher.setPhone(phone);
        newTeacher.setEmail(email);
        repository.save(newTeacher);
        assertEquals(4, repository.count());

        Optional<Teacher> savedTeacher = repository.findByNip(nip);
        assertTrue(savedTeacher.isPresent());
        assertEquals(firstName, savedTeacher.get().getFirstName());
        assertEquals(nip, savedTeacher.get().getNip());
        assertEquals(phone, savedTeacher.get().getPhone());
        assertEquals(true, savedTeacher.get().getIsActive());

        String newFirstName = "Joseph";
        UUID savedTeacherId = savedTeacher.get().getId();
        savedTeacher.get().setFirstName(newFirstName);
        repository.save(savedTeacher.get());

        Optional<Teacher> updatedTeacher = repository.findById(savedTeacherId);
        assertTrue(updatedTeacher.isPresent());
        assertEquals(savedTeacherId, updatedTeacher.get().getId());
        assertEquals(newFirstName, updatedTeacher.get().getFirstName());
    }

    @Test
    void testDelete() {
        List<Teacher> actualData = repository.findAll();
        assertEquals(3, actualData.size());

        Teacher firstTeacher = actualData.get(0);
        assertTrue(actualData.contains(firstTeacher));

        repository.delete(firstTeacher);
        actualData = repository.findAll();
        assertEquals(2, actualData.size());
        assertFalse(actualData.contains(firstTeacher));
    }

}
