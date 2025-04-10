package com.example.banksystem;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import com.example.banksystem.bankPackages.CurrentAccount;
import com.example.banksystem.bankPackages.FixedDepositAccount;
import com.example.banksystem.bankPackages.SavingsAccount;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import com.example.banksystem.bankPackages.Transaction;

public class HelloController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField account_name;

    @FXML
    private ComboBox<String> account_types;

    @FXML
    private TextField deposit_amount;

    @FXML
    private Label message;

    @FXML
    private Label message1;

    @FXML
    private Label message2;

    @FXML
    private Label message3;

    @FXML
    private TextField number_of_transactions;

    @FXML
    private TextField opening_balance;

    @FXML
    private TextField overdraft_limit;

    @FXML
    private TextField withdrawal_amount;

    @FXML
    private TextField name;

    @FXML
    private TextField balance;

    @FXML
    private TextField type;

    @FXML
    private TextField ovd_limit;

    @FXML
    private TextField acc_num;

    @FXML
    private TableView<Transaction> transactionsTable;

    @FXML
    private TableColumn<Transaction, Date> timestampColumn;

    @FXML
    private TableColumn<Transaction, Double> amountColumn;

    @FXML
    private TableColumn<Transaction, String> accNumberColumn;

    @FXML
    private TableColumn<Transaction, String> typeColumn;



    private SavingsAccount savingsAccount;
    private CurrentAccount currentAccount;
    private FixedDepositAccount fixedDepositAccount;

    // Creation of bank accounts
    @FXML
    void createUserBankAccount(ActionEvent event) {
        double balance = 0.0;
        double overdraftLimit = 0.0;
        String accountName = account_name.getText();

        if (!opening_balance.getText().isEmpty()) {
            try {
                balance = Double.parseDouble(opening_balance.getText());
            } catch (Exception e) {
                message.setText("Error: " + e.getMessage());
            }
        }
        String accountType = account_types.getSelectionModel().getSelectedItem();
        if (!overdraft_limit.getText().isEmpty()) {
            try {
                overdraftLimit = Double.parseDouble(overdraft_limit.getText());
            } catch (Exception e) {
                message.setText("Error: " + e.getMessage());
            }
        }

        if (opening_balance.getText().isEmpty()) {
            message.setText("Invalid balance.");
        } else {
            // Create bank account
            if (Objects.equals(accountType, "SAVINGS ACCOUNT")) {
                this.savingsAccount = new SavingsAccount(accountType,accountName, balance);
                message.setText(String.format("Account created successfully.\n Name: %s\n Account Number: %s \n Balance: %2f\n",
                        this.savingsAccount.getAccountHolderName(),
                        this.savingsAccount.getAccountNumber(),
                        this.savingsAccount.getBalance()));
            } else if (Objects.equals(accountType, "CURRENT ACCOUNT")) {
                this.currentAccount = new CurrentAccount(accountType,accountName, balance, overdraftLimit);
                message.setText(String.format("Account created successfully.\n Name: %s\n Account Number: %s \n Balance: %2f\n",
                        this.currentAccount.getAccountHolderName(),
                        this.currentAccount.getAccountNumber(),
                        this.currentAccount.getBalance()));
            } else if (Objects.equals(accountType, "FIXED DEPOSIT ACCOUNT")) {
                this.fixedDepositAccount = new FixedDepositAccount(accountType,accountName, balance, new Date());
                message.setText(String.format("Account created successfully.\n Name: %s\n Account Number: %s \n Balance: %2f\n",
                        this.fixedDepositAccount.getAccountHolderName(),
                        this.fixedDepositAccount.getAccountNumber(),
                        this.fixedDepositAccount.getBalance()));
            }
        }
    }

    @FXML
    void deposit(ActionEvent event) {
        double amount = 0.0;
        String response;
        String strAmount = deposit_amount.getText();
        if (!strAmount.isEmpty()) {
            try {
                amount = Double.parseDouble(strAmount);
            } catch (Exception e) {
                message1.setText("Error: " + e.getMessage());
            }
        }

        if (savingsAccount != null) {
            response = savingsAccount.deposit(amount);
            savingsAccount.createTransaction(savingsAccount.getAccountNumber(), amount, new Date(), "DEPOSIT");
            message1.setText(response);
        } else if (currentAccount != null) {
            response = currentAccount.deposit(amount);
            currentAccount.createTransaction(currentAccount.getAccountNumber(), amount, new Date(), "DEPOSIT");
            message1.setText(response);
        } else if (fixedDepositAccount != null) {
            response = fixedDepositAccount.deposit(amount);
            fixedDepositAccount.createTransaction(fixedDepositAccount.getAccountNumber(), amount, new Date(), "DEPOSIT");
            message1.setText(response);
        }
    }

    @FXML
    void withdraw(ActionEvent event) {
        String strAmount;
        double amount = 0.0;
        String response;

        strAmount = withdrawal_amount.getText();

        if (!strAmount.isEmpty()) {
            try {
                amount = Double.parseDouble(strAmount);
            } catch (Exception e) {
                message2.setText("Error: " + e.getMessage());
            }
        }

        if (savingsAccount != null) {
            response = savingsAccount.withdrawal(amount);
            savingsAccount.createTransaction(savingsAccount.getAccountNumber(), amount, new Date(), "WITHDRAWAL");
            message2.setText(response);
        } else if (currentAccount != null) {
            response = currentAccount.withdrawal(amount);
            currentAccount.createTransaction(currentAccount.getAccountNumber(), amount, new Date(), "WITHDRAWAL");
            message2.setText(response);
        } else if (fixedDepositAccount != null) {
            response = fixedDepositAccount.withdrawal(amount);
            fixedDepositAccount.createTransaction(fixedDepositAccount.getAccountNumber(), amount, new Date(), "WITHDRAWAL");
            message2.setText(response);
        }
    }

    @FXML
    void getTransactions(ActionEvent event) {
        String strCounter = number_of_transactions.getText();
        int counter = 0;

        try {
            counter = Integer.parseInt(strCounter);
        } catch (Exception e) {
            message3.setText("Error: " + e.getMessage());
            return;
        }

        List<Transaction> transactions = null;

        if (savingsAccount != null) {
            transactions = savingsAccount.getLastNTransactions(counter);
        } else if (currentAccount != null) {
            transactions = currentAccount.getLastNTransactions(counter);
        } else if (fixedDepositAccount != null) {
            transactions = fixedDepositAccount.getLastNTransactions(counter);
        }

        System.out.println("TRANSACTIONS: "+transactions);

        if (transactions != null) {
            ObservableList<Transaction> transactionData = FXCollections.observableArrayList(transactions);
            if (transactionsTable != null) {
                transactionsTable.setItems(transactionData);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize comboBox
        account_types.setItems(FXCollections.observableArrayList(
                "SAVINGS ACCOUNT", "CURRENT ACCOUNT", "FIXED DEPOSIT"
        ).sorted());

        // Initialize table columns
        timestampColumn.setCellValueFactory(new PropertyValueFactory<>("timeStamp"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        accNumberColumn.setCellValueFactory(new PropertyValueFactory<>("accountNumber"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("transactionType"));
    }

    public void fetchAccountDetails(Event event) {
        String account;
        //Display the user information
        if (savingsAccount != null) {
            name.setText(savingsAccount.getAccountHolderName());
            type.setText(savingsAccount.getAccountType());
            balance.setText(String.format("%2f", savingsAccount.getBalance()));
            acc_num.setText(savingsAccount.getAccountNumber());
            ovd_limit.setText("Not available");
        } else if (currentAccount != null) {
            name.setText(currentAccount.getAccountHolderName());
            type.setText(currentAccount.getAccountType());
            balance.setText(String.format("%2f", currentAccount.getBalance()));
            acc_num.setText(currentAccount.getAccountNumber());
            ovd_limit.setText(String.format("%2f", currentAccount.overdraftLimit));
        } else if (fixedDepositAccount != null) {
            name.setText(fixedDepositAccount.getAccountHolderName());
            type.setText(fixedDepositAccount.getAccountType());
            balance.setText(String.format("%2f", fixedDepositAccount.getBalance()));
            acc_num.setText(fixedDepositAccount.getAccountNumber());
            ovd_limit.setText("Not available");
        }
    }
}
