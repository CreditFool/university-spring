package com.creditfool.university_spring.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudentTest {

    private Student student;

    private UUID id = UUID.randomUUID();
    private String firstName = "Marta";
    private String lastName = "Rohan";
    private String nim = "1234567890";
    private String address = "lorem ipsum";
    private String phone = "081234567890";
    private String email = "xxx@yyy.zzz";

    @BeforeEach
    void setup() {
        student = Student.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .nim(nim)
                .address(address)
                .phone(phone)
                .email(email)
                .build();
    }

    @Test
    void testBuild() {
        assertNotNull(student);
        assertNotNull(student.getCreatedAt());
        assertNull(student.getUpdatedAt());
        assertNull(student.getDeletedAt());

        assertEquals(firstName, student.getFirstName());
        assertEquals(email, student.getEmail());
    }

    @Test
    void testUpdateProperties() {
        String newFirstName = "Kokoro";
        String newLastName = "Tsurumaki";
        LocalDateTime updateTime = LocalDateTime.now();

        assertEquals(firstName, student.getFirstName());
        assertEquals(lastName, student.getLastName());
        assertNull(student.getUpdatedAt());

        student.setFirstName(newFirstName);
        student.setLastName(newLastName);
        student.setUpdatedAt(updateTime);

        assertEquals(newFirstName, student.getFirstName());
        assertEquals(newLastName, student.getLastName());
        assertEquals(updateTime, student.getUpdatedAt());
    }

}
