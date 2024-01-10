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
    private TableColumn<DataModel, String> speedDownloadColumn;

    @FXML
    private TableColumn<DataModel, String> speedUploadColumn;

    @FXML
    private TableColumn<DataModel, String> speedPingColumn;

    @FXML
    private TableColumn<DataModel, String> speedIpColumn;

    @FXML
    private TableColumn<DataModel, String> speedTimeColumn;

    @FXML
    private TableColumn<DataModel, String> speedHostColumn;

    @FXML
    private TableColumn<DataModel, String> speedCityColumn;

    @FXML
    private TableColumn<DataModel, String> speedCountryColumn;

    @FXML
    private Label showUserID;

    private int loggedInUserId = 0;
    public HistoryController() {
    }


    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        speedDownloadColumn.setCellValueFactory(new PropertyValueFactory<>("speedDownload"));
        speedUploadColumn.setCellValueFactory(new PropertyValueFactory<>("speedUpload"));
        speedPingColumn.setCellValueFactory(new PropertyValueFactory<>("speedPing"));
        speedIpColumn.setCellValueFactory(new PropertyValueFactory<>("speedIp"));
        speedTimeColumn.setCellValueFactory(new PropertyValueFactory<>("speedTime"));
        speedHostColumn.setCellValueFactory(new PropertyValueFactory<>("speedHost"));
        speedCityColumn.setCellValueFactory(new PropertyValueFactory<>("speedCity"));
        speedCountryColumn.setCellValueFactory(new PropertyValueFactory<>("speedCountry"));
    }

    public void setLoggedInUserId(int loggedInUserId) {
        this.loggedInUserId = loggedInUserId;
    }

    public static class DataModel {
        private final Integer id;
        private final String speedDownload;
        private final String speedUpload;
        private final String speedPing;
        private final String speedIp;
        private final String speedTime;
        private final String speedHost;
        private final String speedCity;
        private final String speedCountry;

        public DataModel(Integer id, String speedDownload, String speedUpload, String speedPing, String speedIp, String speedtime,String speedHost, String speedCity, String speedCountry) {
            this.id = id;
            this.speedDownload = speedDownload;
            this.speedUpload = speedUpload;
            this.speedPing = speedPing;
            this.speedIp = speedIp;
            this.speedTime = speedtime;
            this.speedHost = speedHost;
            this.speedCity = speedCity;
            this.speedCountry = speedCountry;
        }

        public Integer getId() {
            return id;
        }

        public String getSpeedDownload() {
            return speedDownload;
        }

        public String getSpeedUpload() {
            return speedUpload;
        }

        public String getSpeedPing() {
            return speedPing;
        }

        public String getSpeedIp() {
            return speedIp;
        }
        public String getSpeedTime() {
            return speedTime;
        }

        public String getSpeedHost() {
            return speedHost;
        }

        public String getSpeedCity() {
            return speedCity;
        }

        public String getSpeedCountry() {
            return speedCountry;
        }
// Add getters for all fields here
        // For brevity, not included in this example
    }
    @FXML
    void connectButton() {
        System.out.println(loggedInUserId);
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String connectQuery = "SELECT su.user_id,sd.speed_download, sd.speed_upload, sd.speed_ping, sd.speed_ip, sd.speed_time, sd.speed_host, sd.speed_city, sd.speed_country\n" +
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
                        queryOutput.getInt("user_id"),
                        queryOutput.getString("speed_download"),
                        queryOutput.getString("speed_upload"),
                        queryOutput.getString("speed_ping"),
                        queryOutput.getString("speed_ip"),
                        queryOutput.getString("speed_time"),
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

}