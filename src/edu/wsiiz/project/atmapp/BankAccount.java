package edu.wsiiz.project.atmapp;

public class BankAccount {
    private final int id;
    private double balance;
    private final String accountNumber;
    private final Customer customer;

    public BankAccount(int id, double balance, String accountNumber, Customer customer) {
        this.id = id;
        this.balance = balance;
        this.accountNumber = accountNumber;
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        balance -= amount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getCustomerName() {
        return customer.name();
    }
}
