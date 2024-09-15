package com.creditfool.university_spring.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TeacherTest {

    private Teacher teacher;

    private UUID id = UUID.randomUUID();
    private String firstName = "Marta";
    private String lastName = "Rohan";
    private String nip = "1234567890";
    private String address = "lorem ipsum";
    private String phone = "081234567890";
    private String email = "xxx@yyy.zzz";

    @BeforeEach
    void setup() {
        teacher = Teacher.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .nip(nip)
                .address(address)
                .phone(phone)
                .email(email)
                .build();
    }

    @Test
    void testBuild() {
        assertNotNull(teacher);
        assertNotNull(teacher.getCreatedAt());
        assertNull(teacher.getUpdatedAt());
        assertNull(teacher.getDeletedAt());

        assertEquals(firstName, teacher.getFirstName());
        assertEquals(email, teacher.getEmail());
    }

    @Test
    void testUpdateProperties() {
        String newFirstName = "Kokoro";
        String newLastName = "Tsurumaki";
        LocalDateTime updateTime = LocalDateTime.now();

        assertEquals(firstName, teacher.getFirstName());
        assertEquals(lastName, teacher.getLastName());
        assertNull(teacher.getUpdatedAt());

        teacher.setFirstName(newFirstName);
        teacher.setLastName(newLastName);
        teacher.setUpdatedAt(updateTime);

        assertEquals(newFirstName, teacher.getFirstName());
        assertEquals(newLastName, teacher.getLastName());
        assertEquals(updateTime, teacher.getUpdatedAt());
    }

}
