package com.example.test_mang.Controller;

import com.example.test_mang.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectDBController {
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
    public void connectButton(ActionEvent event) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String connectQuery = "SELECT sd.speed_download, sd.speed_upload, sd.speed_ping, sd.speed_ip, sd.speed_time, sd.speed_host, sd.speed_city, sd.speed_country\n" +
                "FROM speed_user su\n" +
                "INNER JOIN speed_data sd ON su.user_id = sd.user_id\n" +
                "WHERE su.user_id = 1;";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(connectQuery);

            while (queryOutput.next()) {
//                showUsernameLabel.setText(queryOutput.getString("user_id"));
                showIpLabel.setText(queryOutput.getString("speed_ip"));
                showHostLabel.setText(queryOutput.getString("speed_host"));
                showCityLabel.setText(queryOutput.getString("speed_city"));
                showCountryLabel.setText(queryOutput.getString("speed_country"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
    public void updateLabels(String username,String ip, String host, String city, String country) {
        // Set the received data to the labels in Speed.fxml
        showUsernameLabel.setText(username);
        showIpLabel.setText(ip);
        showHostLabel.setText(host);
        showCityLabel.setText(city);
        showCountryLabel.setText(country);
    }

}
