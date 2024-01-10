package com.example.test_mang.Controller;

import com.example.test_mang.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

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
}
