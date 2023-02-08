package edu.wsiiz.project.atmapp;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ObjectCreator {
    public static QueryExecutor query() {
        return new QueryExecutor();
    }
    public ATM createATM(String q) throws SQLException {
        ResultSet rs = query().executeQuery(q);
        if (rs.next()) {
            int id = rs.getInt("id");
            double cash = rs.getDouble("cashInMachine");
            query().close();
            return new ATM(id, cash);
        }
        query().close();
        throw new SQLException("There is no such ATM!");
    }

    public Customer createCustomer(String q) throws SQLException {
        ResultSet rs = query().executeQuery(q);
        if (rs.next()) {
            String name = rs.getString("name");
            String surname = rs.getString("surname");
            String address = rs.getString("address");
            String phone = rs.getString("phone_number");
            int acc_id = rs.getInt("account_id");
            String fullName = name + " " + surname;
            query().close();
            return new Customer(fullName,address, phone, acc_id);
        }
        query().close();
        return null;
    }

    public BankAccount createAccount(String q, Customer c) throws SQLException {
        ResultSet rs = query().executeQuery(q);
        if (rs.next()) {
            int id = rs.getInt("id");
            double balance = rs.getDouble("balance");
            String accountNumber = rs.getString("accountNumber");
            query().close();
            return new BankAccount(id,balance,accountNumber, c);
        }
        query().close();
        throw new SQLException("There is no such account!");
    }
}
