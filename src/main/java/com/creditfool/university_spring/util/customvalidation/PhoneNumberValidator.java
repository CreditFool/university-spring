package com.creditfool.university_spring.util.customvalidation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

    // /**
    //  * Checks if the given string is a valid phone number.
    //  * 
    //  * @param value the phone number to be validated
    //  * @param context the context in which the constraint is evaluated
    //  * 
    //  * @return true if the phone number is valid, false otherwise
    //  */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        boolean isNumeric = value.chars().allMatch(Character::isDigit);
        return isNumeric && value.length() >= 11 && value.length() <= 13;
    }

}
