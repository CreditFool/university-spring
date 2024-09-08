package com.creditfool.university_spring.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

import com.creditfool.university_spring.entity.Subject;

@SpringBootTest
class SubjectRepositoryIntegrationTest {

    @Autowired
    SubjectRepository repository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    private List<Subject> baseData = new ArrayList<>(List.of(
            new Subject(null, "Math", true),
            new Subject(null, "Programming", true),
            new Subject(null, "Physics", false)));

    @BeforeEach
    void setup() {
        repository.saveAll(baseData);
    }

    @AfterEach
    void clear() {
        jdbcTemplate.execute("TRUNCATE TABLE subject");
    }

    @Test
    void testFindAll() {
        List<Subject> actualData = repository.findAll();
        assertEquals(3, actualData.size());
    }

    @Test
    void testFindByIsActive() {
        List<Subject> activeSubjects = repository.findByIsActive(true);
        assertEquals(2, activeSubjects.size());

        List<Subject> notActiveSubjects = repository.findByIsActive(false);
        assertEquals(1, notActiveSubjects.size());
    }

    @Test
    void testFindByName() {
        Optional<Subject> subject = repository.findByName("Math");
        assertTrue(subject.isPresent());

        subject = repository.findByName("math");
        assertTrue(subject.isPresent());

        subject = repository.findByName("physics");
        assertFalse(subject.isPresent());
    }

    @Test
    void testFindById() {
        List<Subject> actualData = repository.findAll();
        for (Subject actual : actualData) {
            assertEquals(actual, repository.findById(actual.getId()).get());
        }

        assertFalse(repository.findById(UUID.randomUUID()).isPresent());
    }

    @Test
    void testSave() {
        String newSubjectName = "Biology";

        Subject subject = new Subject();
        subject.setSubjectName(newSubjectName);

        List<Subject> subjectList = repository.findAll();
        assertEquals(3, subjectList.size());

        Subject savedSubject = repository.save(subject);
        subjectList = repository.findAll();
        assertEquals(4, subjectList.size());
        assertEquals(savedSubject, repository.findById(savedSubject.getId()).get());
        assertNotNull(savedSubject.getId());
        assertEquals(newSubjectName, savedSubject.getSubjectName());
        assertTrue(savedSubject.getIsActive());

        String updateSubjectName = "Cooking";
        savedSubject.setSubjectName(updateSubjectName);

        Subject updatedSubject = repository.save(savedSubject);
        assertEquals(4, repository.count());
        assertEquals(savedSubject.getId(), updatedSubject.getId());
        assertEquals(updateSubjectName, updatedSubject.getSubjectName());
    }

    @Test
    void testDelete() {
        List<Subject> actualData = repository.findAll();
        assertEquals(3, actualData.size());

        Subject firstSubject = actualData.get(0);
        assertTrue(actualData.contains(firstSubject));

        repository.delete(firstSubject);
        actualData = repository.findAll();
        assertEquals(2, actualData.size());
        assertFalse(actualData.contains(firstSubject));
    }
}
