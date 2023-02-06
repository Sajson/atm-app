package edu.wsiiz.project.atmapp;

import java.sql.*;
import java.util.Scanner;

public class ATMRunner {
    public static Scanner getFromKeyboard() {
        return new Scanner(System.in);
    }
    public static QueryExecutor query() throws SQLException {
        return new QueryExecutor();
    }
    public static void main(String[] args) throws SQLException {
        ResultSet rs = query().executeQuery("SELECT * FROM customers");
        Customer me = new Customer("Adrian", "Poland", "555");
        BankAccount acc1 = new BankAccount(2500, "2137", me, 2115);
        ATM atm1 = new ATM(10000);
        System.out.println("Provide PIN to card!");
        if(atm1.insertCard(acc1,getFromKeyboard().nextDouble())) {
            int option = 0;
            Menu menu = new Menu(atm1);
            while (option != 4) {
                menu.displayOptions();
                option = getFromKeyboard().nextInt();
                menu.handleOption(option);
            }
        }
    }
}
