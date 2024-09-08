package com.creditfool.university_spring.util.customvalidation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean isNumeric = value.chars().allMatch(Character::isDigit);
        return isNumeric && value.length() >= 11 && value.length() <= 13;
    }

}
