package com.creditfool.university_spring.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudentTest {

    private Student studentA;

    private UUID id = UUID.randomUUID();
    private String firstName = "Marta";
    private String lastName = "Rohan";
    private String nip = "1234567890";
    private String address = "lorem ipsum";
    private String phone = "081234567890";
    private String email = "xxx@yyy.zzz";

    @BeforeEach
    void setup() {
        studentA = new Student();
        studentA.setId(id);
        studentA.setFirstName(firstName);
        studentA.setLastName(lastName);
        studentA.setNim(nip);
        studentA.setAddress(address);
        studentA.setPhone(phone);
        studentA.setEmail(email);
    }

    @Test
    void testEquals() {
        assertEquals(true, studentA.equals(studentA));
        assertEquals(studentA, studentA);

        Student studentB = new Student(id, firstName, lastName, nip, address, phone, email, null);
        assertEquals(true, studentA.equals(studentB));
    }

    @Test
    void testNotEquals() {
        Student studentB = new Student();
        assertEquals(false, studentA.equals(studentB));
    }

    @Test
    void testGetAddress() {
        assertEquals(address, studentA.getAddress());
    }

    @Test
    void testGetEmail() {
        assertEquals(email, studentA.getEmail());
    }

    @Test
    void testGetFirstName() {
        assertEquals(firstName, studentA.getFirstName());
    }

    @Test
    void testGetId() {
        assertEquals(id, studentA.getId());
    }

    @Test
    void testGetIsActive() {
        assertEquals(true, studentA.getIsActive());
    }

    @Test
    void testGetLastName() {
        assertEquals(lastName, studentA.getLastName());
    }

    @Test
    void testGetNim() {
        assertEquals(nip, studentA.getNim());
    }

    @Test
    void testGetPhone() {
        assertEquals(phone, studentA.getPhone());
    }

    @Test
    void testSetAddress() {
        String newAddress = "new address";
        studentA.setAddress(newAddress);
        assertEquals(newAddress, studentA.getAddress());
    }

    @Test
    void testSetEmail() {
        String newEmail = "new@mail.zzz";
        studentA.setEmail(newEmail);
        assertEquals(newEmail, studentA.getEmail());
    }

    @Test
    void testSetFirstName() {
        String newFirstName = "new first";
        studentA.setFirstName(newFirstName);
        assertEquals(newFirstName, studentA.getFirstName());
    }

    @Test
    void testSetId() {
        UUID newId = UUID.randomUUID();
        studentA.setId(newId);
        assertEquals(newId, studentA.getId());
    }

    @Test
    void testSetIsActive() {
        studentA.setIsActive(false);
        assertEquals(false, studentA.getIsActive());
    }

    @Test
    void testSetLastName() {
        String newLastName = "new last";
        studentA.setLastName(newLastName);
        assertEquals(newLastName, studentA.getLastName());
    }

    @Test
    void testSetNim() {
        String newNim = "091232456312";
        studentA.setNim(newNim);
        assertEquals(newNim, studentA.getNim());
    }

    @Test
    void testSetPhone() {
        String newPhone = "866-570-6605 x3015";
        studentA.setPhone(newPhone);
        assertEquals(newPhone, studentA.getPhone());
    }

}
