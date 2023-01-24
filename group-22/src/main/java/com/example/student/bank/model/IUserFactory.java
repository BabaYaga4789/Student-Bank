package com.example.student.bank.model;

import com.example.student.bank.model.authentication.IAuthentication;
import com.example.student.bank.model.authentication.IUser;
import com.example.student.bank.model.validator.IValidation;
import com.example.student.bank.model.transaction.ISend;
import com.example.student.bank.database.persistance.IUserPersistance;

public interface IUserFactory {

    public IAuthentication makeAuthentication();

    public IUser makeUser();

    public IUserPersistance makeUserPersistance();

    public IValidation makeNameValidation();

    public IValidation makePasswordValidation();

    public ISend makePayment();

}
