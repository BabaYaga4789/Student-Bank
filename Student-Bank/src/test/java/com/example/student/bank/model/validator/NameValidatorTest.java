package com.example.student.bank.model.validator;

import org.junit.jupiter.api.Test;

class NameValidatorTest {

    @Test
    void validateInvalidTest() {
        String name = "abc@";
        IValidation validation = new NameValidator();
        assert (!validation.validate(name));
    }

    @Test
    void validateValidTest() {
        String name = "abc";
        IValidation validation = new NameValidator();
        assert (validation.validate(name));
    }
}