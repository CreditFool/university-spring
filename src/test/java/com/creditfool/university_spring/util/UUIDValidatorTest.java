package com.creditfool.university_spring.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

class UUIDValidatorTest {
    @Test
    void testIsValid() {
        String validUUID = String.valueOf(UUID.randomUUID());
        String invalidUUID = "1234567";

        assertTrue(UUIDValidator.isValid(validUUID));
        assertFalse(UUIDValidator.isValid(invalidUUID));
    }
}
