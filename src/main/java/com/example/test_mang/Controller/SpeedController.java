//package com.example.test_mang;
//
//import javafx.application.Platform;
//import javafx.scene.chart.XYChart;
//
//public class SpeedController {
//    private SpeedApplication speedApp;
//    private boolean isRunning;
//
//    public SpeedController(SpeedApplication speedApp) {
//        this.speedApp = speedApp;
//        this.isRunning = true;
//    }
//
//    public void startTracking() {
//        new Thread(() -> {
//            while (isRunning) {
//                double uploadSpeed = generateRandomSpeed();
//                double downloadSpeed = generateRandomSpeed();
//                double ping = generateRandomPing();
//
//                Platform.runLater(() -> {
//                    speedApp.updateChart(uploadSpeed, downloadSpeed, ping);
//                });
//
//                try {
//                    Thread.sleep(1000); // 10 seconds
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//    }
//
//    private double generateRandomSpeed() {
//        return Math.random() * 100; // Generating random speed values for upload/download
//    }
//
//    private double generateRandomPing() {
//        return Math.random() * 50; // Generating random ping values
//    }
//
//    public void stopTracking() {
//        isRunning = false;
//    }
//}

package com.example.test_mang.Controller;

import java.io.BufferedReader;

import com.example.test_mang.DatabaseConnection;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.example.test_mang.SpeedApplication;
import javafx.application.Platform;

public class SpeedController {
    private SpeedApplication speedApp;
    private boolean isRunning;

    public SpeedController(SpeedApplication speedApp) {
        this.speedApp = speedApp;
        this.isRunning = true;
    }

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

    public void setLoggedInUserId(int userId) {
        loggedInUserId = userId;
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
                System.out.println("User Id"+ loggedInUserId);
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
    }


