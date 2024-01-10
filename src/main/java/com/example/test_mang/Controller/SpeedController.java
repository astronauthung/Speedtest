
package com.example.test_mang.Controller;

import com.example.test_mang.DatabaseConnection;
import com.example.test_mang.SpeedApplication;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONObject;
public class SpeedController {
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
    @FXML
    private SpeedApplication speedApp;
    private boolean isRunning;

    public SpeedController(SpeedApplication speedApp) {
        this.speedApp = speedApp;
        this.isRunning = true;
    }

    public SpeedController(){}

    public void startTracking() {
        new Thread(() -> {
            while (isRunning) {
                startSpeedTest();
                try {
                    Thread.sleep(1000); // 10 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void stopTracking() {
        isRunning = false;
    }

    private int loggedInUserId = 0;

    @FXML
    private void connectButton(ActionEvent event) {
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


    public void updateLabels(String username, int userID) {
        showUsernameLabel.setText(username);
        showUserID.setText(String.valueOf(userID));
        loggedInUserId = Integer.parseInt(showUserID.getText());
    }

    @FXML
    private void showHistoryPopup(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/test_mang/HistoryPopup.fxml"));
            Parent root = loader.load();
            HistoryController historyController = loader.getController(); // Update to HistoryController

            // You should call the method in HistoryController to connect data
            historyController.setLoggedInUserId(loggedInUserId);

            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setTitle("History");
            popupStage.setScene(new Scene(root));
            popupStage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @FXML
    private void showSignupPopup(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/test_mang/SignupPopup.fxml"));
            Parent signupPopup = loader.load();
            SignupPopupController signupPopupController = loader.getController();

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
            LoginPopupController loginPopupController = loader.getController();

            loginPopupController.setSpeedController(this);


            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setTitle("Login");
            popupStage.setScene(new Scene(loginPopup));
            popupStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startSpeedTest() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command("speedtest", "--json");

            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line);
            }

            int exitVal = process.waitFor();
            if (exitVal == 0) {
                String outputString = output.toString();
                JSONObject jsonObject = new JSONObject(outputString);

                // Extract speeds in bits per second
                double downloadSpeedBps = jsonObject.getDouble("download");
                double uploadSpeedBps = jsonObject.getDouble("upload");

                // Convert speeds to megabits per second
                double downloadSpeedMbps = downloadSpeedBps / 1_000_000;
                double uploadSpeedMbps = uploadSpeedBps / 1_000_000;

                double ping = jsonObject.getDouble("ping");
                String ipAddress = jsonObject.getJSONObject("client").getString("ip");
                String host = jsonObject.getJSONObject("server").getString("host");
                String city = jsonObject.getJSONObject("server").getString("name");
                String country = jsonObject.getJSONObject("server").getString("country");

                // For testing or debugging, print the extracted data
                System.out.println("User Id");
                System.out.println("Download speed: " + downloadSpeedMbps + " Mbps");
                System.out.println("Upload speed: " + uploadSpeedMbps + " Mbps");
                System.out.println("Ping: " + ping + " ms");
                System.out.println("IP Address: " + ipAddress);
                System.out.println("Server Host: " + host);
                System.out.println("City: " + city);
                System.out.println("Country: " + country);

                DatabaseConnection connectNow = new DatabaseConnection();
                Connection connectDB = connectNow.getConnection();

                String insertQuery = "INSERT INTO speed_data (user_id, speed_download, speed_upload, speed_ping, speed_ip, speed_host, speed_city, speed_country) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

                try {
                    PreparedStatement preparedStatement = connectDB.prepareStatement(insertQuery);
                    preparedStatement.setInt(1, loggedInUserId);
                    preparedStatement.setDouble(2, downloadSpeedMbps);
                    preparedStatement.setDouble(3, uploadSpeedMbps);
                    preparedStatement.setDouble(4, ping);
                    preparedStatement.setString(5, ipAddress);
                    preparedStatement.setString(6, host);
                    preparedStatement.setString(7, city);
                    preparedStatement.setString(8, country);

                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                    connectDB.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                // Update the chart or UI with the obtained speed data
                Platform.runLater(() -> {
                    speedApp.updateChart(uploadSpeedMbps, downloadSpeedMbps, ping);
                });
            } else {
                System.out.println("Speedtest command failed with exit code: " + exitVal);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setLoggedInUserId(int id) {
        loggedInUserId = id;
    }

    public void clearChart(ActionEvent actionEvent) {
        Platform.runLater(() -> {
            speedApp.clearChartData();
        });
    }
}


