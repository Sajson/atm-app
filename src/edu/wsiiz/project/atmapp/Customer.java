package edu.wsiiz.project.atmapp;

public record Customer(String name, String address, String phoneNumber, int account_id) {
    public int getAccount_id() {
        return account_id;
    }
}
