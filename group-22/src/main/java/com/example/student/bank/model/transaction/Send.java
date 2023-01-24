package com.example.student.bank.model.transaction;

import com.example.student.bank.database.persistance.IUserPersistance;

import java.util.ArrayList;
import java.util.List;

public class Send implements ISend {

    private String receiverAccountNumber;
    private String senderAccountNumber;
    private float senderAccountBalance;
    private float transferAmount;
    private String receiverEmail;
    private String transferDate;

    public Send() {

    }

    public String getReceiverAccountNumber() {
        return receiverAccountNumber;
    }

    public void setReceiverAccountNumber(String receiverAccountNumber) {
        this.receiverAccountNumber = receiverAccountNumber;
    }

    public float getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(float transferAmount) {
        this.transferAmount = transferAmount;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public String getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(String transferDate) {
        this.transferDate = transferDate;
    }

    public String getSenderAccountNumber() {
        return senderAccountNumber;
    }

    public void setSenderAccountNumber(String senderAccountNumber) {
        this.senderAccountNumber = senderAccountNumber;
    }

    public float getSenderAccountBalance() {
        return senderAccountBalance;
    }

    public void setSenderAccountBalance(float senderAccountBalance) {
        this.senderAccountBalance = senderAccountBalance;
    }

    @Override
    public void sendMoney(IUserPersistance userPersistance, String text, Send send) {
        userPersistance.sendMoney(text, send);
    }

    @Override
    public void accountDetails(IUserPersistance userPersistance, Send send, String text) {
        userPersistance.accountDetail(text, send);
    }

    @Override
    public List<Send> transferDetails(IUserPersistance userPersistance, Send send, String text) {
        List<Send> sendList = new ArrayList<>();
        sendList.addAll(userPersistance.transferDetails(text, send));
        return sendList;
    }
}
