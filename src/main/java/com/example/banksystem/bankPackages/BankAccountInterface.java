package com.example.banksystem.bankPackages;

public interface BankAccountInterface {
    String deposit(double amount);
    String withdrawal(double amount);
    double interestCalculation(double rate);
}
