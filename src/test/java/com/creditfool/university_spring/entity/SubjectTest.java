package com.creditfool.university_spring.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

class SubjectTest {

    private Subject subjectA;

    private UUID id = UUID.randomUUID();
    private String subjectName = "Math";

    @BeforeEach
    void setup() {
        subjectA = new Subject();
        subjectA.setId(id);
        subjectA.setSubjectName(subjectName);
    }

    @Test
    void testEquals() {
        assertEquals(true, subjectA.equals(subjectA));
        assertEquals(subjectA, subjectA);

        Subject subjectB = new Subject(id, subjectName, true);
        assertEquals(true, subjectA.equals(subjectB));
    }

    @Test
    void testGetId() {
        assertEquals(id, subjectA.getId());
    }

    @Test
    void testGetIsActive() {
        assertTrue(subjectA.getIsActive());
    }

    @Test
    void testGetSubjectName() {
        assertEquals(subjectName, subjectA.getSubjectName());
    }

    @Test
    void testSetId() {
        UUID newId = UUID.randomUUID();
        subjectA.setId(newId);
        assertEquals(newId, subjectA.getId());
    }

    @Test
    void testSetIsActive() {
        subjectA.setIsActive(false);
        assertFalse(subjectA.getIsActive());
    }

    @Test
    void testSetSubjectName() {
        String newSubjectName = "Data Structure";
        subjectA.setSubjectName(newSubjectName);
        assertEquals(newSubjectName, subjectA.getSubjectName());
    }
}
