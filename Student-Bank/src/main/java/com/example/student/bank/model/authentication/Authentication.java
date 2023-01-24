package com.example.student.bank.model.authentication;

import com.example.student.bank.database.persistance.IUserPersistance;

public class Authentication implements IAuthentication {

    @Override
    public boolean register(IUserPersistance userPersistence, User user) {
        userPersistence.registration(user);
        return true;
    }

    @Override
    public boolean login(IUserPersistance userPersistence, User user, String userName, String password) {
        if ((userPersistence.login(user).getUserEmail().equals(userName)) && (userPersistence.login(user).getPassword().equals(password))) {
            return true;
        } else {
            return false;
        }
    }

}
