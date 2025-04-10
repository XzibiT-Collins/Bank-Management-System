package com.example.banksystem;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class WithdrawalController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button deposit_view;

    @FXML
    private Button details_view;

    @FXML
    private Button withdraw;

    @FXML
    private TextField withdrawal_amount;

    @FXML
    private Button withdrawal_view;

    @FXML
    void depositView(ActionEvent event) {

    }

    @FXML
    void detailsView(ActionEvent event) {

    }

    @FXML
    void initiateWithdrawal(ActionEvent event) {

    }

    @FXML
    void withdrawalView(ActionEvent event) {

    }

    @FXML
    void initialize() {

        assert details_view != null : "fx:id=\"details_view\" was not injected: check your FXML file 'withdrawal.fxml'.";
        assert withdraw != null : "fx:id=\"withdraw\" was not injected: check your FXML file 'withdrawal.fxml'.";
        assert withdrawal_amount != null : "fx:id=\"withdrawal_amount\" was not injected: check your FXML file 'withdrawal.fxml'.";
        assert withdrawal_view != null : "fx:id=\"withdrawal_view\" was not injected: check your FXML file 'withdrawal.fxml'.";

    }

}

