package com.example.banksystem.bankPackages;

import java.util.Date;

public class FixedDepositAccount extends BankAccount {
    private Date maturityDate;

    public FixedDepositAccount(String accountType,String accountHolderName, double balance, Date maturityDate) {
        super(accountType,accountHolderName,balance);
        this.maturityDate = maturityDate;
    }

    //getter for maturityDte
    public String getMaturityDate(){
        return this.maturityDate.toString();
    }

    @Override
    public String deposit(double amount) {
        String message;
        if(amount <= 0.0){ // prevent deposit amount less than 0.0
            message = "Invalid amount.";
        }else{
            this.updateBalance(amount); // use setter to update account balance
            message = "Deposit of: "+ amount +" successful";
        }
        return message;
    }

    @Override
    public String withdrawal(double amount) {
        String message;
        //Get current date
        Date currentDate = new Date();

        //check if date is before maturityDate
        if(currentDate.before(maturityDate)){
            message = "Withdrawal denied. Try again on or after maturity date:"+ maturityDate.toString() + ".";
        }else if(amount <= 0.00){
            message = "Withdrawal amount cannot be equal or less than 0.00";
        }else if(amount > getBalance()){
            message = "Insufficient balance.";
        }
        else{
            updateBalance(-amount); // deduct amount from balance
            message = "Withdrawal of: "+ amount +" successful.";
        }
        return message;
    }

    //Interest calculation
    @Override
    public double interestCalculation(double rate){
        if(rate<=0){
            System.out.print("Rate cannot be 0%");
            return 0.0;
        }
        //calculate and update balance with interest
        double interest = (rate/100)*getBalance();

        //update balance using setter
        updateBalance(interest);
        return interest; // return interest value
    }

}
