package com.example.banksystem.bankPackages;

import java.util.Date;

public class SavingsAccount extends BankAccount{


    public SavingsAccount(String accountType,String accountHolderName, double balance) {
        super(accountType,accountHolderName, balance);

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
        //check potential remaining balance and make sure it is not lower than minimum balance
        double estimatedBalance = (getBalance() - amount);

        // Minimum balance on savings account
        double minimumBalance = 500.00;

        if(amount > getBalance()){ // Prevent withdrawal when amount exceeds balance
            message = "Withdrawal amount cannot exceed account balance";
        }else if(estimatedBalance < minimumBalance){ // do not allow overdraft
            message = "Insufficient balance minimum balance of "+ minimumBalance +"must be maintained";
        }else{ // update the balance
            this.updateBalance(-amount); // subtract amount from balance to reflect withdrawal
            message = "Withdrawal of: "+ amount +" successfully processed";
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
