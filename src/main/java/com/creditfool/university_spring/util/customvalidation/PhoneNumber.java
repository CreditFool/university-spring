package com.creditfool.university_spring.util.customvalidation;

import java.lang.annotation.*;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = PhoneNumberValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PhoneNumber {
    String message() default "Use valid phone number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}