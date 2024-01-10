package com.example.test_mang.Controller;

import com.example.test_mang.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class ConnectDBController {
    @FXML
    private Label showUsernameLabel;
    @FXML
    private Label showUserID;
    @FXML
    private Label showIpLabel;
    @FXML
    private Label showHostLabel;
    @FXML
    private Label showCityLabel;
    @FXML
    private Label showCountryLabel;

    public void connectButton(ActionEvent event) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String connectQuery = "SELECT sd.speed_download, sd.speed_upload, sd.speed_ping, sd.speed_ip, sd.speed_time, sd.speed_host, sd.speed_city, sd.speed_country\n" +
                "FROM speed_user su\n" +
                "INNER JOIN speed_data sd ON su.user_id = sd.user_id\n" +
                "WHERE su.user_id = ?";

        String userId = showUserID.getText();
        try {
            PreparedStatement preparedStatement = connectDB.prepareStatement(connectQuery);
            preparedStatement.setString(1, userId);
            ResultSet queryOutput = preparedStatement.executeQuery();

            if (queryOutput.next()) {
                showIpLabel.setText(queryOutput.getString("speed_ip"));
                showHostLabel.setText(queryOutput.getString("speed_host"));
                showCityLabel.setText(queryOutput.getString("speed_city"));
                showCountryLabel.setText(queryOutput.getString("speed_country"));
            } else {
                System.out.println("Dont have data with id: " + userId);
                showIpLabel.setText("");
                showHostLabel.setText("");
                showCityLabel.setText("");
                showCountryLabel.setText("");
            }

            queryOutput.close();
            preparedStatement.close();
            connectDB.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showSignupPopup(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/test_mang/SignupPopup.fxml"));
            Parent signupPopup = loader.load();
            SignupPopupController signupPopupController = loader.getController();

            signupPopupController.setConnectDBController(this);

            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setTitle("Signup");
            popupStage.setScene(new Scene(signupPopup));
            popupStage.showAndWait();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    @FXML
    private void showLoginPopup(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/test_mang/loginPopup.fxml"));

            Parent loginPopup = loader.load();
            // Get the controller from the loader
            LoginPopupController loginPopupController = loader.getController();

            // Set the ConnectDBController instance
            loginPopupController.setConnectDBController(this);

            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setTitle("Login");
            popupStage.setScene(new Scene(loginPopup));
            popupStage.showAndWait();

            // You may handle the results from the login popup here
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateLabels(String username, String userID) {
        showUsernameLabel.setText(username);
        showUserID.setText(userID);
    }

}
