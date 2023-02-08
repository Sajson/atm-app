package edu.wsiiz.project.atmapp;

// Customer is like record in the database, converted with knowledge from https://www.baeldung.com/java-record-keyword
public record Customer(String name, String address, String phoneNumber, int account_id) {
    public int getAccount_id() {
        return account_id;
    }
}
