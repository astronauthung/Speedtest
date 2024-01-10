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
    private Label showUsernameLabel;
    @FXML
    private Label showIpLabel;
    @FXML
    private Label showHostLabel;
    @FXML
    private Label showCityLabel;
    @FXML
    private Label showCountryLabel;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;
    public void setConnectDBController(ConnectDBController connectDBController) {
        this.connectDBController = connectDBController;
    }

    // Method to handle successful login
    public void handleSuccessfulLogin(String username,String ip, String host, String city, String country) {
        connectDBController.updateLabels(username,ip, host, city, country);
    }

    @FXML
    private void submitLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Database connection parameters
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String connectQuery = "SELECT su.user_id, sd.speed_ip, sd.speed_host, sd.speed_city, sd.speed_country\n" +
                "FROM speed_user su\n" +
                "INNER JOIN speed_data sd ON su.user_id = sd.user_id\n" +
                "WHERE su.user_name = ? AND su.user_pass = ?";

        try {
            PreparedStatement preparedStatement = connectDB.prepareStatement(connectQuery);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet queryOutput = preparedStatement.executeQuery();

            if (queryOutput.next()) {
                // Valid credentials, login successful
                String ip = queryOutput.getString("speed_ip");
                String host = queryOutput.getString("speed_host");
                String city = queryOutput.getString("speed_city");
                String country = queryOutput.getString("speed_country");

                // Call method in ConnectDBController to update labels in Speed.fxml
                handleSuccessfulLogin(username,ip, host, city, country);

                // Show success message in UI
                showSuccessMessage();

                System.out.println("Login successful!");

                // Close the login window after a short delay
                closeLoginWindow();
            } else {
                // Invalid credentials, display an error message
                System.out.println("Invalid credentials. Please try again!");
                showErrorDialog();
            }

            connectDB.close(); // Close the connection after usage
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
