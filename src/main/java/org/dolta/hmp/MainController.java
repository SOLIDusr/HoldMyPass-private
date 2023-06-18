package org.dolta.hmp;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.dolta.hmp.utils.DBConnection;
import java.net.URL;
import java.util.ResourceBundle;

import static org.dolta.hmp.utils.DBConnection.SetUp;

// Class of controller
public class MainController {
//  FXML Variables
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
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Application name error");
            alert.setHeaderText("Inappropriate name or length ");
            alert.setContentText("Application name should not contain spaces and length 3<l<16");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    return;
                }
            });
        }
        SetUp();

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
