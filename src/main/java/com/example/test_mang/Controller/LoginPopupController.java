package com.example.test_mang.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import com.example.test_mang.DatabaseConnection;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPopupController {
    @FXML
    private ConnectDBController connectDBController;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;
    @FXML
    private Label showUsernameLabel;
    public void setConnectDBController(ConnectDBController connectDBController) {
        this.connectDBController = connectDBController;
    }

    public void handleSuccessfulLogin(String username, String id) {
        connectDBController.updateLabels(username, id);
    }
    @FXML
    private void submitLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String connectQuery = "select * from speed_user where user_name='"+ username +"' and user_pass= '" + password +"'";


        try {
            PreparedStatement preparedStatement = connectDB.prepareStatement(connectQuery);

            ResultSet queryOutput = preparedStatement.executeQuery();

            if (queryOutput.next()) {
                String id = queryOutput.getString("user_id");
                System.out.println(id);

                handleSuccessfulLogin(username, id);

                showSuccessMessage();

                System.out.println("Login successful!");

                closeLoginWindow();
            } else {
                System.out.println("Invalid credentials. Please try again!");
                showErrorDialog();
            }

            connectDB.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void showErrorDialog() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Login Failed");
        errorAlert.setHeaderText(null);
        errorAlert.setContentText("Invalid credentials. Please try again!");
        errorAlert.showAndWait();
    }
    private void closeLoginWindow() {
        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.close();
    }

    private void showSuccessMessage() {
        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle("Login Successful");
        successAlert.setHeaderText(null);
        successAlert.setContentText("Welcome, " + usernameField.getText());
        successAlert.showAndWait();
    }

}
