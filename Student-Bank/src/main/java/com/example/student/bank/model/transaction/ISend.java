package com.example.student.bank.model.transaction;

import com.example.student.bank.database.persistance.IUserPersistance;

import java.util.List;

public interface ISend {

    void sendMoney(IUserPersistance userPersistance, String text, Send send);

    void accountDetails(IUserPersistance userPersistance, Send send, String text);

    List<Send> transferDetails(IUserPersistance userPersistance, Send send, String text);
}
