package com.example.student.bank.model.authentication;

import com.example.student.bank.database.persistance.IUserPersistance;

public interface IUser {
    boolean getUserAccountDetails(IUserPersistance userPersistance, User user);
}
