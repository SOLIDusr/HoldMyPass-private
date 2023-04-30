package org.dolta.hmp;


import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;


public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> accountSelector;

    @FXML
    private Button addAccountButton;

    @FXML
    private Button addAppButton;

    @FXML
    private TextField appNameTextField;

    @FXML
    private TableView<?> logTable;

    @FXML
    private TextField loginTextField;

    @FXML
    private TextField passTextField;

    @FXML
    private Button showMeButton;

    @FXML
    private void setupAppButton() {
        String appName = appNameTextField.getText();
        if (appName.contains(" ") || !(appName.length() < 16 && appName.length() > 3)) {
            return;
        }
        accountSelector.setItems(FXCollections.observableArrayList(appName));
    }

    @FXML
    private void setShowMeButton() {
        String selected = accountSelector.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void initialize() {
        assert accountSelector != null : "fx:id=\"accountSelector\" was not injected: check your FXML file 'window-main.fxml'.";
        assert addAccountButton != null : "fx:id=\"addAccountButton\" was not injected: check your FXML file 'window-main.fxml'.";
        assert addAppButton != null : "fx:id=\"addAppButton\" was not injected: check your FXML file 'window-main.fxml'.";
        assert appNameTextField != null : "fx:id=\"appNameTextField\" was not injected: check your FXML file 'window-main.fxml'.";
        assert logTable != null : "fx:id=\"logTable\" was not injected: check your FXML file 'window-main.fxml'.";
        assert loginTextField != null : "fx:id=\"loginTextField\" was not injected: check your FXML file 'window-main.fxml'.";
        assert passTextField != null : "fx:id=\"passTextField\" was not injected: check your FXML file 'window-main.fxml'.";
        assert showMeButton != null : "fx:id=\"showMeButton\" was not injected: check your FXML file 'window-main.fxml'.";

        addAppButton.setOnAction(event -> setupAppButton());

    }

}
