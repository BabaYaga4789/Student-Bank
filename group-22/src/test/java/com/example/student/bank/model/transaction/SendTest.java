package com.example.student.bank.model.transaction;

import com.example.student.bank.database.UserPersistanceMock;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class SendTest {

    UserPersistanceMock userPersistanceMock = new UserPersistanceMock();
    Send send = new Send();
    List<Send> list = new ArrayList<>();

    @Test
    void sendMoneyInvalidTest() {
        String userName = "123@gmail.com";
        assert (!userPersistanceMock.sendMoney(userName, send));
    }

    @Test
    void sendMoneyValidTest() {
        String userName = "abc@gmail.com";
        assert (userPersistanceMock.sendMoney(userName, send));
    }

    @Test
    void accountInvalidDetails() {
        String userName = "123@gmail.com";
        assert (!(userPersistanceMock.accountDetail(userName, send)));
    }

    @Test
    void accountValidDetails() {
        String userName = "abc@gmail.com";
        assert (userPersistanceMock.accountDetail(userName, send));
    }

    @Test
    void transferDetailsInvalidTest() {
        String userName = "123@gmail.com";
        assert (!(userPersistanceMock.transferDetails(userName, send)));
    }

    @Test
    void transferDetailsValidTest() {
        String userName = "abc@gmail.com";
        assert (userPersistanceMock.transferDetails(userName, send));
    }
}