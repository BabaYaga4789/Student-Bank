package com.example.student.bank.model;

import com.example.student.bank.model.authentication.Authentication;
import com.example.student.bank.model.authentication.IAuthentication;
import com.example.student.bank.model.authentication.IUser;
import com.example.student.bank.model.authentication.User;
import com.example.student.bank.model.validator.IValidation;
import com.example.student.bank.model.validator.NameValidator;
import com.example.student.bank.model.validator.PasswordValidator;
import com.example.student.bank.model.transaction.ISend;
import com.example.student.bank.model.transaction.Send;
import com.example.student.bank.database.persistance.UserPersistance;

public class UserFactory implements IUserFactory {

    private static UserFactory instance = null;

    public static UserFactory Instance() {
        if (null == instance) {
            instance = new UserFactory();
        }
        return instance;
    }

    @Override
    public IAuthentication makeAuthentication() {
        Authentication authentication = new Authentication();
        return authentication;
    }

    @Override
    public IUser makeUser() {
        User user = new User();
        return user;
    }

    @Override
    public UserPersistance makeUserPersistance() {
        UserPersistance userPersistance = new UserPersistance();
        return userPersistance;
    }

    @Override
    public IValidation makeNameValidation() {
        NameValidator nameValidator = new NameValidator();
        return nameValidator;
    }

    @Override
    public IValidation makePasswordValidation() {
        PasswordValidator passwordValidator = new PasswordValidator();
        return passwordValidator;
    }

    @Override
    public ISend makePayment() {
        Send send = new Send();
        return send;
    }


}
