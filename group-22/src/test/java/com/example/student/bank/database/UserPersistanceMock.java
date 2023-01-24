package com.example.student.bank.database;

import com.example.student.bank.model.authentication.User;
import com.example.student.bank.model.transaction.Send;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserPersistanceMock {

    HashMap<String, User> mockDataBase = new HashMap<>();
    List<Send> sendlist = new ArrayList<>();
    User user = new User();

    public UserPersistanceMock() {
        user.setUserEmail("abc@gmail.com");
        user.setPassword("123456789");
        user.setAge(12);
        user.setAddress("asdfghjkl");
        user.setAccountType("savings");
        user.setFirstName("user1");
        user.setLastName("user1");
        user.setInitialDepositAmount(1000);
        mockDataBase.put(user.getUserEmail(), user);
    }

    public boolean registration(User user) {
        if (mockDataBase.containsKey(user.getUserEmail())) {
            return false;
        } else {
            mockDataBase.put(user.getUserEmail(), user);
            return true;
        }
    }

    public boolean login(String text) {
        if (mockDataBase.containsKey(text)) {
            return true;
        } else {
            return false;
        }
    }

    public User homePage(String user) {
        if (mockDataBase.containsKey(user)) {
            return mockDataBase.get(user);
        } else {
            return null;
        }
    }

    public Boolean accountDetail(String text, Send send) {
        if (mockDataBase.containsKey(text)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean sendMoney(String text, Send send) {
        if (mockDataBase.containsKey(text)) {
            return true;
        } else {
            return false;
        }

    }

    public boolean transferDetails(String text, Send send) {
        if (mockDataBase.containsKey(text)) {
            return true;
        } else {
            return false;
        }
    }
}
