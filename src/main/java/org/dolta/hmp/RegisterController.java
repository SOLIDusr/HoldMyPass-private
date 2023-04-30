package org.dolta.hmp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.dolta.hmp.utils.Log;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;

public class RegisterController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button enterButton;

    @FXML
    private Label errorText;

    @FXML
    private Label haveOneText;

    @FXML
    private TextField loginTextField;

    @FXML
    private TextField mailTextField;

    @FXML
    private TextField passwordTextField;

    Runnable runnable = (() -> {
        enterButton.setDisable(true);
        try {
            Thread.sleep(600000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        enterButton.setDisable(false);
    });

    void signIn() throws SQLException, InterruptedException {
        if (!(errorText.getText().equals(""))) {
            errorText.setText("Something went wrong! \nTry again later or contact DEV");
            return;
        }
        boolean response = RegLogValidator.regProvider(loginTextField.getText(), passwordTextField.getText(), mailTextField.getText());
        if (response) {
            Log.fine("Successfully added new account to database");
        } else {
            Log.bad("Something went wrong");
        }
    }
    void register() throws SQLException, InterruptedException {
        String enteredPassword = passwordTextField.getText();
        String eMail = mailTextField.getText();
        if (!(RegLogValidator.emailValid(eMail))) {
            errorText.setText("Email is not valid!");
            Log.warn("User provided wrong mail");
            Log.warn(eMail);
            return;
        }
        int passCode = RegLogValidator.passwordValid(enteredPassword);
        switch (passCode) {
            case 0 -> {
                errorText.setText("");
                signIn();
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
        assert mailTextField != null : "fx:id=\"MailTextField\" was not injected: check your FXML file 'window-register.fxml'.";
        assert passwordTextField != null : "fx:id=\"PasswordTextField\" was not injected: check your FXML file 'window-register.fxml'.";
        assert enterButton != null : "fx:id=\"enterButton\" was not injected: check your FXML file 'window-register.fxml'.";
        assert errorText != null : "fx:id=\"errorText\" was not injected: check your FXML file 'window-register.fxml'.";
        assert loginTextField != null : "fx:id=\"loginTextField\" was not injected: check your FXML file 'window-register.fxml'.";
        assert haveOneText != null : "fx:id=\"haveOneText\" was not injected: check your FXML file 'window-register.fxml'.";
        enterButton.setOnAction(event -> {
            try {
                register();
            } catch (SQLException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}

