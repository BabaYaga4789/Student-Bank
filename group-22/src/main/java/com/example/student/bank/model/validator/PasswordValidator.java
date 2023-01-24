package com.example.student.bank.model.validator;

public class PasswordValidator implements IValidation {
    @Override
    public boolean validate(String text) {
        boolean passCheck;
        if (text.length() < 8) {
            passCheck = false;
        } else {
            passCheck = true;
        }
        return passCheck;
    }
}
