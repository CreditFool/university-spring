package com.creditfool.university_spring.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TeacherTest {

    private Teacher teacherA;

    private UUID id = UUID.randomUUID();
    private String firstName = "Marta";
    private String lastName = "Rohan";
    private String nip = "1234567890";
    private String address = "lorem ipsum";
    private String phone = "081234567890";
    private String email = "xxx@yyy.zzz";

    @BeforeEach
    void setup() {
        teacherA = new Teacher();
        teacherA.setId(id);
        teacherA.setFirstName(firstName);
        teacherA.setLastName(lastName);
        teacherA.setNip(nip);
        teacherA.setAddress(address);
        teacherA.setPhone(phone);
        teacherA.setEmail(email);
    }

    @Test
    void testEquals() {
        assertEquals(true, teacherA.equals(teacherA));
    }

    @Test
    void testNotEquals() {
        Teacher teacherB = new Teacher();
        assertEquals(false, teacherA.equals(teacherB));
    }

    @Test
    void testGetAddress() {
        assertEquals(address, teacherA.getAddress());
    }

    @Test
    void testGetEmail() {
        assertEquals(email, teacherA.getEmail());
    }

    @Test
    void testGetFirstName() {
        assertEquals(firstName, teacherA.getFirstName());
    }

    @Test
    void testGetId() {
        assertEquals(id, teacherA.getId());
    }

    @Test
    void testGetIsActive() {
        assertEquals(true, teacherA.getIsActive());
    }

    @Test
    void testGetLastName() {
        assertEquals(lastName, teacherA.getLastName());
    }

    @Test
    void testGetNip() {
        assertEquals(nip, teacherA.getNip());
    }

    @Test
    void testGetPhone() {
        assertEquals(phone, teacherA.getPhone());
    }

    @Test
    void testSetAddress() {
        String newAddress = "new address";
        teacherA.setAddress(newAddress);
        assertEquals(newAddress, teacherA.getAddress());
    }

    @Test
    void testSetEmail() {
        String newEmail = "new@mail.zzz";
        teacherA.setEmail(newEmail);
        assertEquals(newEmail, teacherA.getEmail());
    }

    @Test
    void testSetFirstName() {
        String newFirstName = "new first";
        teacherA.setFirstName(newFirstName);
        assertEquals(newFirstName, teacherA.getFirstName());
    }

    @Test
    void testSetId() {
        UUID newId = UUID.randomUUID();
        teacherA.setId(newId);
        assertEquals(newId, teacherA.getId());
    }

    @Test
    void testSetIsActive() {
        teacherA.setIsActive(false);
        assertEquals(false, teacherA.getIsActive());
    }

    @Test
    void testSetLastName() {
        String newLastName = "new last";
        teacherA.setLastName(newLastName);
        assertEquals(newLastName, teacherA.getLastName());
    }

    @Test
    void testSetNip() {
        String newNip = "091232456312";
        teacherA.setNip(newNip);
        assertEquals(newNip, teacherA.getNip());
    }

    @Test
    void testSetPhone() {
        String newPhone = "866-570-6605 x3015";
        teacherA.setPhone(newPhone);
        assertEquals(newPhone, teacherA.getPhone());
    }

}
