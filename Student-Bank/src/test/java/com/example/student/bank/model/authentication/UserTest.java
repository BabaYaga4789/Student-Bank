package com.example.student.bank.model.authentication;

import com.example.student.bank.database.UserPersistanceMock;
import org.junit.jupiter.api.Test;

class UserTest {
    UserPersistanceMock userPersistanceMock = new UserPersistanceMock();

    @Test
    void getUserAccountDetailsInvalidTest() {
        String userEmail = "123@gmail.com";
        assert (userPersistanceMock.homePage(userEmail) == null);
    }

    @Test
    void getUserAccountDetailsValidTest() {
        String userEmail = "abc@gmail.com";
        assert (userPersistanceMock.homePage(userEmail) != null);
    }
}