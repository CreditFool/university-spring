package com.creditfool.university_spring.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.creditfool.university_spring.dto.SubjectDto;
import com.creditfool.university_spring.entity.Subject;
import com.creditfool.university_spring.exception.DataAlreadyExistException;
import com.creditfool.university_spring.exception.DataNotFoundException;
import com.creditfool.university_spring.service.SubjectService;

@SpringBootTest
class SubjectServiceImplIntegrationTest {

    @Autowired
    SubjectService service;

    @BeforeEach
    void setup() {
        service.createSubject(new SubjectDto(null, "Math"));
        service.createSubject(new SubjectDto(null, "Programming"));
    }

    @AfterEach
    void clear() {
        List<Subject> savedList = service.getAllSubject();
        for (Subject subject : savedList) {
            service.deleteSubject(String.valueOf(subject.getId()), true);
        }
    }

    @Test
    void testCreateSubject() {
        List<Subject> totalList = service.getAllSubject();
        assertEquals(2, totalList.size());

        String subjectName = "Astronomy";
        SubjectDto newSubject = new SubjectDto(null, subjectName);
        Subject savedSubject = service.createSubject(newSubject);
        totalList = service.getAllSubject();
        assertEquals(3, totalList.size());

        try {
            SubjectDto notValidSubject = new SubjectDto(null, "astronomy");
            assertNull(service.createSubject(notValidSubject));

        } catch (RuntimeException e) {
            assertEquals(DataAlreadyExistException.class, e.getClass());
            assertEquals("Subject with same name already exist", e.getMessage(),
                    "Must fail because same Name for active subject found");
        }

        service.deleteSubject(String.valueOf(savedSubject.getId()));
        service.createSubject(new SubjectDto(null, "astronomy"));
        totalList = service.getAllSubject();
        assertEquals(4, totalList.size());

        List<Subject> notActiveList = service.getAllSubject(false);
        assertEquals(1, notActiveList.size());
    }

    @Test
    void testDeleteSubject() {
        String subjectName = "Astronomy";
        SubjectDto newSubject = new SubjectDto(null, subjectName);
        Subject savedSubject = service.createSubject(newSubject);

        List<Subject> totalList = service.getAllSubject();
        List<Subject> activeList = service.getAllSubject(true);
        List<Subject> notActiveList = service.getAllSubject(false);
        assertEquals(3, totalList.size());
        assertEquals(3, activeList.size());
        assertEquals(0, notActiveList.size());

        service.deleteSubject(String.valueOf(savedSubject.getId()));
        totalList = service.getAllSubject();
        activeList = service.getAllSubject(true);
        notActiveList = service.getAllSubject(false);
        assertEquals(3, totalList.size());
        assertEquals(2, activeList.size());
        assertEquals(1, notActiveList.size());

        service.deleteSubject(String.valueOf(savedSubject.getId()), true);
        totalList = service.getAllSubject();
        activeList = service.getAllSubject(true);
        notActiveList = service.getAllSubject(false);
        assertEquals(2, totalList.size());
        assertEquals(2, activeList.size());
        assertEquals(0, notActiveList.size());
    }

    @Test
    void testGetAllSubject() {
        List<Subject> actualList = service.getAllSubject();
        assertEquals(2, actualList.size());

        actualList = service.getAllSubject(true);
        assertEquals(2, actualList.size());

        actualList = service.getAllSubject(false);
        assertEquals(0, actualList.size());
    }

    @Test
    void testGetSubjectById() {
        List<Subject> actualList = service.getAllSubject();
        for (Subject subject : actualList) {
            assertEquals(subject, service.getSubjectById(String.valueOf(subject.getId()), true));
        }

        try {
            Subject invalidSubject = service.getSubjectById("123456", true);
            assertNull(invalidSubject);

        } catch (RuntimeException e) {
            assertEquals(DataNotFoundException.class, e.getClass());
            assertEquals("Subject not found", e.getMessage(), "Must fail because subject found");

        }
    }

    @Test
    void testGetSubjectByName() {
        String subjectName = "Astronomy";

        SubjectDto newSubject = new SubjectDto(null, subjectName);
        service.createSubject(newSubject);

        Subject savedSubject = service.getSubjectByName(subjectName);
        assertNotNull(savedSubject);
        assertEquals(subjectName, savedSubject.getSubjectName());

        try {
            Subject invalidSubject = service.getSubjectByName("Civic");
            assertNull(invalidSubject);

        } catch (RuntimeException e) {
            assertEquals(DataNotFoundException.class, e.getClass());
            assertEquals("Subject not found", e.getMessage(), "Must fail because subject not found");

        }
    }

    @Test
    void testUpdateSubject() {
        String usedSubjectName = "math";

        String oldSubjectName = "gArdening";
        String newSubjectName = "Gardening";
        SubjectDto newSubject = new SubjectDto(null, oldSubjectName);
        service.createSubject(newSubject);
        List<Subject> actualList = service.getAllSubject();
        assertEquals(3, actualList.size());

        Subject savedSubject = service.getSubjectByName(newSubjectName);
        assertEquals(oldSubjectName, savedSubject.getSubjectName());

        Subject updatedSubject = service.updateSubject(String.valueOf(savedSubject.getId()),
                new SubjectDto(savedSubject.getId(), newSubjectName));
        assertEquals(newSubjectName, updatedSubject.getSubjectName());

        try {
            SubjectDto notValidSubject = new SubjectDto(savedSubject.getId(), usedSubjectName);
            assertNull(service.updateSubject(String.valueOf(savedSubject.getId()), notValidSubject));

        } catch (RuntimeException e) {
            assertEquals(DataAlreadyExistException.class, e.getClass());
            assertEquals("Subject with same name already exist", e.getMessage(),
                    "Must fail because same Name for active subject found");
        }
    }
}
