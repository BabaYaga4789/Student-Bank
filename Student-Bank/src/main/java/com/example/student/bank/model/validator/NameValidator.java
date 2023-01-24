package com.example.student.bank.model.validator;

public class NameValidator implements IValidation {
    @Override
    public boolean validate(String text) {
        boolean check;
        if (text.matches("[a-zA-Z]+")) {
            check = true;
        } else {
            check = false;
        }
        return check;
    }
}
