package com.example.student.bank.database.persistance;

import com.example.student.bank.model.authentication.User;
import com.example.student.bank.model.transaction.Send;

import java.util.List;

public interface IUserPersistance {
    public boolean registration(User user);

    public User login(User user);

    public User homePage(User user);

    public Send accountDetail(String text, Send send);

    public void sendMoney(String text, Send send);

    public List<Send> transferDetails(String text, Send send);
}
