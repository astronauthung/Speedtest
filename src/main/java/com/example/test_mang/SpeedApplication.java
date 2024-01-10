package com.example.test_mang;

import com.example.test_mang.Controller.SpeedController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class SpeedApplication extends Application {
    private SpeedController speedController;
    private XYChart.Series<Number, Number> uploadSeries;
    private XYChart.Series<Number, Number> downloadSeries;
    private XYChart.Series<Number, Number> pingSeries;

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Speed.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Network Speed Tracker");
        primaryStage.setScene(new Scene(root, 1335, 750));
        primaryStage.show();

        LineChart<Number, Number> lineChart = (LineChart<Number, Number>) root.lookup("#lineChart");
        NumberAxis xAxis = (NumberAxis) lineChart.getXAxis();
        xAxis.setLabel("Time");
        NumberAxis yAxis = (NumberAxis) lineChart.getYAxis();
        yAxis.setLabel("Speed/Ping");

        uploadSeries = new XYChart.Series<>();
        uploadSeries.setName("Upload Speed");
        downloadSeries = new XYChart.Series<>();
        downloadSeries.setName("Download Speed");
        pingSeries = new XYChart.Series<>();
        pingSeries.setName("Ping");


        lineChart.getData().addAll(uploadSeries, downloadSeries, pingSeries);

        speedController = new SpeedController(this);
        speedController.startTracking();
    }

    public void updateChart(double uploadSpeed, double downloadSpeed, double ping) {
        uploadSeries.getData().add(new XYChart.Data<>(uploadSeries.getData().size(), uploadSpeed));
        downloadSeries.getData().add(new XYChart.Data<>(downloadSeries.getData().size(), downloadSpeed));
        pingSeries.getData().add(new XYChart.Data<>(pingSeries.getData().size(), ping));

        // Limit the data points to maintain a certain window, e.g., last 10 points
//        if (uploadSeries.getData().size() > 10) {
//            uploadSeries.getData().remove(0);
//            downloadSeries.getData().remove(0);
//            pingSeries.getData().remove(0);
//        }
        uploadSeries.setName(String.format("Upload Speed: %.2f Mbps", uploadSpeed));
        downloadSeries.setName(String.format("Download Speed: %.2f Mbps", downloadSpeed));
        pingSeries.setName(String.format("Ping: %.2f ms", ping));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
