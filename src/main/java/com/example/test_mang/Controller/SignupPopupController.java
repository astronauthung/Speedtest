package com.example.test_mang.Controller;

import com.example.test_mang.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignupPopupController {
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField confirmPasswordField;

    @FXML
    private void submitSignup() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (password.equals(confirmPassword)) {
            System.out.println(username + password + confirmPassword);
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();

            String connectQuery = "INSERT INTO speed_user (user_name, user_pass) VALUES (?, ?)";

            try {
                PreparedStatement preparedStatement = connectDB.prepareStatement(connectQuery);

                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                preparedStatement.executeUpdate();

                showSuccessMessage();
                System.out.println("Signup successful!");

                connectDB.close();

                closeSignupWindow();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Password dont match!");
            showErrorDialog();
        }
    }
    private void showErrorDialog() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Signup Failed");
        errorAlert.setHeaderText(null);
        errorAlert.setContentText("Password dont match. Please try again!");
        errorAlert.showAndWait();
    }
    private void closeSignupWindow() {
        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.close();
    }

    private void showSuccessMessage() {
        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle("Singup Successful");
        successAlert.setHeaderText(null);
        successAlert.setContentText("Welcome, " + usernameField.getText());
        successAlert.showAndWait();
    }
}
