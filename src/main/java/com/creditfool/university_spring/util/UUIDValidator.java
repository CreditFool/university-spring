package com.creditfool.university_spring.util;

import java.util.UUID;

public class UUIDValidator {

    private UUIDValidator() {
    }

    public static boolean isValid(String uuid) {
        try {
            UUID.fromString(uuid);
            return true;

        } catch (Exception e) {
            return false;

        }
    }
}
