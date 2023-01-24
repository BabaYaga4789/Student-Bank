package com.example.student.bank.model.authentication;

import com.example.student.bank.database.persistance.IUserPersistance;

public interface IAuthentication {
    boolean register(IUserPersistance userPersistence, User user);

    boolean login(IUserPersistance userPersistence, User user, String userEmail, String password);

}
