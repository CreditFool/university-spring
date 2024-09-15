package com.creditfool.university_spring.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SubjectTest {

    private Subject subject;

    private UUID id = UUID.randomUUID();
    private String subjectName = "Math";

    @BeforeEach
    void setup() {
        subject = Subject.builder()
                .id(id)
                .name(subjectName)
                .build();
    }

    @Test
    void testBuild() {
        assertNotNull(subject);
        assertNotNull(subject.getCreatedAt());
        assertNull(subject.getUpdatedAt());
        assertNull(subject.getDeletedAt());

        assertEquals(subjectName, subject.getName());
    }

    @Test
    void testUpdateProperties() {
        String newSubjectName = "Computer";
        LocalDateTime updateTime = LocalDateTime.now();

        assertEquals(subjectName, subject.getName());
        assertNull(subject.getUpdatedAt());

        subject.setName(newSubjectName);
        subject.setUpdatedAt(updateTime);

        assertEquals(newSubjectName, subject.getName());
        assertEquals(updateTime, subject.getUpdatedAt());
    }
}
