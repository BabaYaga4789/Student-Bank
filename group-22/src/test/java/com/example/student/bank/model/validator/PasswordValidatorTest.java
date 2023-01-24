package com.example.student.bank.model.validator;

import org.junit.jupiter.api.Test;

class PasswordValidatorTest {

    @Test
    void validateInvalidTest() {
        String password = "123";
        IValidation validation = new PasswordValidator();
        assert (!validation.validate(password));
    }

    @Test
    void validateValidTest() {
        String password = "12345678";
        IValidation validation = new PasswordValidator();
        assert (validation.validate(password));
    }
}