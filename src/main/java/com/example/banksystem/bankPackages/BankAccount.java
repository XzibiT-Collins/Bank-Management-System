package com.example.banksystem.bankPackages;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BankAccount implements BankAccountInterface {
    private static int accountCounter = 10000;
    private String accountHolderName;
    private String accountNumber;
    private String accountType;
    private double balance;
    private TransactionList transactionHistory;

    public BankAccount(String accountType, String accountHolderName, double balance) {
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.transactionHistory = new TransactionList();
        this.accountNumber = generateAccountNumber();
        this.accountType = accountType;
    }

    // getters and setters
    public String getAccountType() {
        return accountType;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double amount) {
        this.balance = amount;
    }

    //update balance on withdrawal and deposit
    public void updateBalance(double amount) {
        this.balance += amount;
    }

    public String generateAccountNumber() {
        return String.format("%09d", accountCounter++);
    }

    //create transaction
    public void createTransaction(String accountNumber, double amount, Date timestamp, String transactionType) {
        Transaction newTransaction = new Transaction(this.accountNumber, amount, timestamp, transactionType);
        transactionHistory.addTransaction(newTransaction);
    }

    //Method to return a list of last N transactions
    public List<Transaction> getLastNTransactions(int counter) {
        List<Transaction> result = new ArrayList<>();

        Transaction lastTransaction = transactionHistory.tail;
        int printed = 0;

        while (lastTransaction != null && printed < counter) {
            result.add(lastTransaction);
            lastTransaction = lastTransaction.prev;
            printed++;
        }
        return result;
    }

    //Methods common to all subclasses forced by Interface
    @Override
    public String deposit(double amount) {
        return "";
    }

    @Override
    public String withdrawal(double amount) {
        return "";
    }

    @Override
    public double interestCalculation(double rate) {
        return rate;
    }
}
