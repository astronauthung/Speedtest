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
    public void connectButton(ActionEvent event) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String connectQuery = "select username from speed_data";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(connectQuery);

            while (queryOutput.next()) {
                showUsernameLabel.setText(queryOutput.getString("username"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
