package org.dolta.hmp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;

import javafx.scene.control.CheckBox;
import org.dolta.hmp.utils.Log;

public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button enterButton;

    @FXML
    private Label errorText;

    @FXML
    private CheckBox keepSessionCheck;

    @FXML
    private TextField loginTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Label singInText;

    static Integer wrongTries = 0;

    Runnable runnable = (() -> {
        enterButton.setDisable(true);
        try {
            Thread.sleep(600000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        enterButton.setDisable(false);
    });

    void logIn() throws SQLException, InterruptedException {
        if (!(errorText.getText().equals(""))) {
            errorText.setText("Something went wrong! \nTry again later or contact DEV");
            Log.warn("Error occurred while trying to login!");
            return;
        }
        boolean response = RegLogValidator.loginProvider(loginTextField.getText(), passwordTextField.getText());
        if (response) {
            Log.fine("Successfully entered an account!");
        } else {
            Log.bad("Wrong account data! Retry!");
            wrongTries += 1;
            Log.bad(String.format("Wrong tries: %d", wrongTries));
            if (wrongTries > 5) {
                CompletableFuture.runAsync(runnable);
            }
        }
    }

    void check_in() throws SQLException, InterruptedException {
        String enteredPassword = passwordTextField.getText();
        int passCode = RegLogValidator.passwordValid(enteredPassword);
        switch (passCode) {
            case 0 -> {
                errorText.setText("");
                logIn();
            }
            case 1 -> errorText.setText("Password should not \nbe empty");
            case 2 -> errorText.setText("Password should be longer \nthan 3 letters and shorter\n than 16 letters");
            case 3 -> errorText.setText("Password should contain \nat least one\n special digit(@-&)");
            case 4 -> errorText.setText("Password should not \ncontain spaces");
            case 5 -> errorText.setText("Password should contain\n at least 1 number");
            case 6 ->
                    errorText.setText("Password should contain \nat least one uppercase(A-Z)\n and lowercase digit(a-z)");
        }

    }

    @FXML
    void initialize() {
        Log.info("App Initialized without errors");
        assert enterButton != null : "fx:id=\"enterButton\" was not injected: check your FXML file 'window-login.fxml'.";
        assert errorText != null : "fx:id=\"errorText\" was not injected: check your FXML file 'window-login.fxml'.";
        assert keepSessionCheck != null : "fx:id=\"keepSessionCheck\" was not injected: check your FXML file 'window-login.fxml'.";
        assert loginTextField != null : "fx:id=\"loginTextField\" was not injected: check your FXML file 'window-login.fxml'.";
        assert passwordTextField != null : "fx:id=\"passwordTextField\" was not injected: check your FXML file 'window-login.fxml'.";
        assert singInText != null : "fx:id=\"singInText\" was not injected: check your FXML file 'window-login.fxml'.";
        enterButton.setOnAction(event -> {
            try {
                check_in();
            } catch (SQLException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}

