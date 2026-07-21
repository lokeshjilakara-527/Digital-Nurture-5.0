package com.cognizant.loanservice.model;

/** A loan (plain POJO — returned as JSON). */
public class Loan {
    private String number;
    private String type;          // HOME / CAR / PERSONAL
    private double amount;
    private double interestRate;
    private String customerName;

    public Loan() { }
    public Loan(String number, String type, double amount, double interestRate, String customerName) {
        this.number = number;
        this.type = type;
        this.amount = amount;
        this.interestRate = interestRate;
        this.customerName = customerName;
    }
    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public double getInterestRate() { return interestRate; }
    public void setInterestRate(double interestRate) { this.interestRate = interestRate; }
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
}
