package com.example.test_mang.Controller;

import com.example.test_mang.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;

public class HistoryController {

    @FXML
    private TableView<DataModel> tableView;

    @FXML
    private TableColumn<DataModel, Integer> idColumn;

    @FXML
    private TableColumn<DataModel, Double> speedDownloadColumn;

    @FXML
    private TableColumn<DataModel, Double> speedUploadColumn;

    @FXML
    private TableColumn<DataModel, Double> speedPingColumn;

    @FXML
    private TableColumn<DataModel, String> speedIpColumn;

    @FXML
    private TableColumn<DataModel, String> speedHostColumn;

    @FXML
    private TableColumn<DataModel, String> speedCityColumn;

    @FXML
    private TableColumn<DataModel, String> speedCountryColumn;

    @FXML
    private Label showUserID;

    private int loggedInUserId = 0;

    public void updateLabels(String username, int userID) {
        showUserID.setText(String.valueOf(userID));
        loggedInUserId = Integer.parseInt(showUserID.getText());
    }
    @FXML
    private void connectButton() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String connectQuery = "SELECT sd.speed_download, sd.speed_upload, sd.speed_ping, sd.speed_ip, sd.speed_time, sd.speed_host, sd.speed_city, sd.speed_country\n" +
                "FROM speed_user su\n" +
                "INNER JOIN speed_data sd ON su.user_id = sd.user_id\n" +
                "WHERE su.user_id = ?";


        try {
            PreparedStatement preparedStatement = connectDB.prepareStatement(connectQuery);
            preparedStatement.setString(1, String.valueOf(loggedInUserId));
            ResultSet queryOutput = preparedStatement.executeQuery();

            // Clear previous table data
            tableView.getItems().clear();

            while (queryOutput.next()) {
                // Create DataModel objects and add them to the table
                DataModel data = new DataModel(
                        queryOutput.getInt("id"),
                        queryOutput.getDouble("speed_download"),
                        queryOutput.getDouble("speed_upload"),
                        queryOutput.getDouble("speed_ping"),
                        queryOutput.getString("speed_ip"),
                        queryOutput.getString("speed_host"),
                        queryOutput.getString("speed_city"),
                        queryOutput.getString("speed_country")
                );
                tableView.getItems().add(data);
            }

            queryOutput.close();
            preparedStatement.close();
            connectDB.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DataModel class representing your data structure
    public static class DataModel {
        private final Integer id;
        private final Double speedDownload;
        private final Double speedUpload;
        private final Double speedPing;
        private final String speedIp;
        private final String speedHost;
        private final String speedCity;
        private final String speedCountry;

        public DataModel(Integer id, Double speedDownload, Double speedUpload, Double speedPing, String speedIp, String speedHost, String speedCity, String speedCountry) {
            this.id = id;
            this.speedDownload = speedDownload;
            this.speedUpload = speedUpload;
            this.speedPing = speedPing;
            this.speedIp = speedIp;
            this.speedHost = speedHost;
            this.speedCity = speedCity;
            this.speedCountry = speedCountry;
        }

        // Add getters for all fields here
        // For brevity, not included in this example
    }
}