package com.example.banksystem.bankPackages;

import java.util.Date;

public class CurrentAccount extends BankAccount{
    private Date date;
    public double overdraftLimit;
    int baseAccountNumber = 200001;
    String accountNumber;


    public CurrentAccount(String accountType,String accountHolderName, double balance, double overdraftLimit) {
        super(accountType,accountHolderName, balance);
        this.overdraftLimit = overdraftLimit;
    }


    @Override
    public String generateAccountNumber(){
        String newAccountNumber = String.format("%09d",baseAccountNumber++); // creates default account number of length 9
        this.accountNumber = newAccountNumber;
        return newAccountNumber;
    }


    @Override
    public String deposit(double amount) {
        String message;

        if(amount < 0.0){
            message = "Invalid amount.";
        }else{
            this.updateBalance(amount);
            message = "Deposit successful.";
        }
        return message;
    }

    @Override
    public String withdrawal(double amount) {
        String message;
        // compute current balance and overdraft
        double availableFunds = getBalance() + overdraftLimit;

        //timestamp
        Date now = new Date();

        if(amount <= 0.00){
            message = "Withdrawal amount cannot be 0.00 or less.";
        }
        else if(amount > availableFunds){
            message = "You cannot withdraw funds above your overdraft limit";
        }else{
            updateBalance(-amount); // deduct withdrawal amount
            message = "Withdrawal of "+ amount + "was successfully processed. Bal: "+ getBalance() + "." + now.toString();
        }
        return message;
    }

    @Override
    public double interestCalculation(double rate) {
        return rate; // Does not do interest calculation
    }
}
