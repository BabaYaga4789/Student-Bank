package com.example.student.bank.database.persistance;

import com.example.student.bank.model.authentication.User;
import com.example.student.bank.model.transaction.Send;
import com.example.student.bank.database.DataBaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserPersistance implements IUserPersistance {

    private Connection connection;
    private ResultSet resultSet;


    public UserPersistance() {
    }

    public boolean registration(User user) {
        try {
            DataBaseConnection databaseConnection = new DataBaseConnection();
            connection = databaseConnection.databaseConnection();
            PreparedStatement insertStmt = connection.prepareStatement("insert into userDetails(userId,userFirstname,userLastName,userEmail,userPassword,userAddress,userPhoneNumber,userGender,userAge,userInitialAmount,userAccountType,userRegistrationDate) values(?,?,?,?,?,?,?,?,?,?,?,?)");
            PreparedStatement updateStmt = connection.prepareStatement("insert into userAccountDetails(userAccountNumber,userAccountType,userAccountBalance,userEmail) select userId,userAccountType,userInitialAmount,userEmail from userDetails where userEmail = ?");
            insertStmt.setLong(1, user.getUserId());
            insertStmt.setString(2, user.getFirstName());
            insertStmt.setString(3, user.getLastName());
            insertStmt.setString(4, user.getUserEmail());
            insertStmt.setString(5, user.getPassword());
            insertStmt.setString(6, user.getAddress());
            insertStmt.setString(7, user.getPhoneNumber());
            insertStmt.setString(8, user.getGender());
            insertStmt.setInt(9, user.getAge());
            insertStmt.setFloat(10, user.getInitialDepositAmount());
            insertStmt.setString(11, user.getAccountType());
            insertStmt.setString(12, user.getDateAndTime());
            updateStmt.setString(1, user.getUserEmail());
            insertStmt.executeUpdate();
            updateStmt.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Register Error: " + e);
        }
        return true;
    }

    @Override
    public User login(User user) {
        try {
            DataBaseConnection dataBaseConnection = new DataBaseConnection();
            connection = dataBaseConnection.databaseConnection();
            PreparedStatement stmt = connection.prepareStatement("select userEmail,userPassword from userDetails where userEmail = ?");
            stmt.setString(1, user.getUserEmail());
            resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                user.setUserEmail(resultSet.getString(1));
                user.setPassword(resultSet.getString(2));
            }
            connection.close();
            System.out.println("login database");
        } catch (SQLException e) {
            System.out.println("Error in connecting to database" + e);
        }
        return user;
    }

    @Override
    public User homePage(User user) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        connection = dataBaseConnection.databaseConnection();
        try {
            PreparedStatement userDetailFetch = connection.prepareStatement("select userAccountNumber,userAccountType,userAccountBalance from userAccountDetails where userEmail = ?");
            userDetailFetch.setString(1, user.getUserEmail());
            resultSet = userDetailFetch.executeQuery();
            while (resultSet.next()) {
                user.setAccountNumber(resultSet.getLong(1));
                user.setAccountType(resultSet.getString(2));
                user.setAccountBalance(resultSet.getFloat(3));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public Send accountDetail(String text, Send send) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        connection = dataBaseConnection.databaseConnection();
        try {
            PreparedStatement userDetailFetch = connection.prepareStatement("select userAccountNumber,userAccountBalance from userAccountDetails where userEmail = ?");
            userDetailFetch.setString(1, text);
            resultSet = userDetailFetch.executeQuery();
            while (resultSet.next()) {
                send.setSenderAccountNumber(resultSet.getString(1));
                send.setSenderAccountBalance(resultSet.getFloat(2));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return send;
    }

    @Override
    public void sendMoney(String text, Send send) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        connection = dataBaseConnection.databaseConnection();
        try {
            PreparedStatement transUpdateSenderQuery = connection.prepareStatement("update userAccountDetails set userAccountBalance = userAccountBalance - ? where userEmail = ?");
            transUpdateSenderQuery.setFloat(1, send.getTransferAmount());
            transUpdateSenderQuery.setString(2, text);
            transUpdateSenderQuery.executeUpdate();
            PreparedStatement transUpdateReceiverQuery = connection.prepareStatement("update userAccountDetails set userAccountBalance = userAccountBalance + ? where userEmail = ?");
            transUpdateReceiverQuery.setFloat(1, send.getTransferAmount());
            transUpdateReceiverQuery.setString(2, send.getReceiverEmail());
            transUpdateReceiverQuery.executeUpdate();
            PreparedStatement insertTransaction = connection.prepareStatement("insert into userTransactionDetails(senderAccountNumber,receiverAccountNumber,transferAmount,senderEmailId,receiverEmailId,transferDate) select s.userID, r.userID, ? ,s.userEmail,r.userEmail,curdate() from userDetails s, userDetails r where s.userEmail = ? and r.userEmail = ?; ");
            insertTransaction.setFloat(1, send.getTransferAmount());
            insertTransaction.setString(2, text);
            insertTransaction.setString(3, send.getReceiverEmail());
            insertTransaction.executeUpdate();
            System.out.println("transaction complete");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Send> transferDetails(String text, Send send) {
        List<Send> sendList = new ArrayList<>();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        connection = dataBaseConnection.databaseConnection();
        try {
            PreparedStatement fetchTransactionDetailsQuery = connection.prepareStatement("select receiverAccountNumber, transferAmount, receiverEmailId, transferDate from userTransactionDetails where senderEmailId = ?");
            fetchTransactionDetailsQuery.setString(1, text);
            resultSet = fetchTransactionDetailsQuery.executeQuery();
            while (resultSet.next()) {
                send.setReceiverAccountNumber(resultSet.getString(1));
                send.setTransferAmount(resultSet.getFloat(2));
                send.setReceiverEmail(resultSet.getString(3));
                send.setTransferDate(resultSet.getString(4));
                sendList.add(send);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sendList;
    }
}
