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
import java.io.IOException;
import java.io.InputStreamReader;

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

    public void startSpeedTest() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command("speedtest", "--json", "--simple");

            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            StringBuilder output = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                output.append(line);
            }

            int exitVal = process.waitFor();
            if (exitVal == 0) {
                String outputString = output.toString();
                // Extract the download speed from the output string
                String downloadSpeedString = outputString.split("Download: ")[1].split(" Mbit/s")[0];
                double downloadSpeed = Double.parseDouble(downloadSpeedString);

                // Extract the upload speed from the output string
                String uploadSpeedString = outputString.split("Upload: ")[1].split(" Mbit/s")[0];
                double uploadSpeed = Double.parseDouble(uploadSpeedString);

                // Extract the ping from the output string
                String pingString = outputString.split("Ping: ")[1].split(" ms")[0];
                double ping = Double.parseDouble(pingString);

                Platform.runLater(() -> {
                    speedApp.updateChart(uploadSpeed, downloadSpeed, ping);
                });

                // Print the download speed, upload speed, and ping to the console for debugging
                System.out.println("Download speed: " + downloadSpeed + " Mbps");
                System.out.println("Upload speed: " + uploadSpeed + " Mbps");
                System.out.println("Ping: " + ping + " ms");
            } else {
                System.out.println("Error 404");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

