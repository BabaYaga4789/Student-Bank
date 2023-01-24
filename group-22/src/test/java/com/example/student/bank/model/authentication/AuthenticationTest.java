package com.example.student.bank.model.authentication;

import com.example.student.bank.database.UserPersistanceMock;
import org.junit.jupiter.api.Test;

class AuthenticationTest {
    User user = new User();
    UserPersistanceMock userPersistenceTest = new UserPersistanceMock();


    @Test
    void registerTest() {
        assert (userPersistenceTest.registration(user));
    }

    @Test
    void loginInvalidTest() {
        String email = "123@gmail.com";
        assert (!userPersistenceTest.login(email));
    }

    @Test
    void loginValidTest() {
        String email = "abc@gmail.com";
        assert (userPersistenceTest.login(email));
    }
}