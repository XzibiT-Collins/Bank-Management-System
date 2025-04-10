package com.example.banksystem.bankPackages;

import java.util.Date;

public class Transaction {
    private Date timeStamp;
    private double amount;
    private String accountNumber;
    private String transactionType;
    Transaction next; // initialize next pointer
    Transaction prev; // initialize previous pointer

    Transaction(String accountNumber, double amount,Date timeStamp,String transactionType){
        this.accountNumber = accountNumber;
        this.timeStamp = timeStamp;
        this.amount = amount;
        this.transactionType =transactionType;
        this.next = null; // set next pointer to null
        this.prev = null;
    }

    //getters and setters
    public double getAmount(){
        return amount;
    }

    public String getAccountNumber(){
        return accountNumber;
    }

    public String getTransactionType(){
        return transactionType;
    }

    public Date getTimeStamp(){
        return timeStamp;
    }
}

class TransactionList{
    Transaction head;
    Transaction tail; // keep track of tail in order not to reverse the whole linked list before getting the last N Transactions

    public void addTransaction(Transaction newTransaction){
        if (head == null) {
            // If the list is empty, both head and tail should point to the new transaction
            head = tail = newTransaction;
        } else {
            // We don't need to traverse since we are tracking the tail
            tail.next = newTransaction;
            newTransaction.prev = tail; // Set the prev pointer of the new transaction to the current tail
            tail = newTransaction; // Update the tail to the new transaction
        }
    }


    public void printAllTransactions(){
        Transaction current = head; // get first transaction in linked list
        while(current != null){ // if there is a transaction print it
            System.out.printf("Timestamp: %s | Amount: %.2f | Account: %s%n",
                    current.getTimeStamp(), current.getAmount(), current.getAccountNumber());
            // Move to next transaction
            current = current.next;
        }
        //Print No transactions found
        System.out.println("No transactions found for this account.");
    }
}
