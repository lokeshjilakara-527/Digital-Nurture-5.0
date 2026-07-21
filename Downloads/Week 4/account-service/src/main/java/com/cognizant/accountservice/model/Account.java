package com.cognizant.accountservice.model;

/** A bank account (plain POJO — returned as JSON). */
public class Account {
    private String number;
    private String type;        // SAVINGS / CURRENT
    private double balance;
    private String customerName;

    public Account() { }
    public Account(String number, String type, double balance, String customerName) {
        this.number = number;
        this.type = type;
        this.balance = balance;
        this.customerName = customerName;
    }
    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
}
