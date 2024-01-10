module com.example.test_mang {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires mysql.connector.j;
    requires org.json;

    opens com.example.test_mang to javafx.fxml;
    exports com.example.test_mang;
    exports com.example.test_mang.Controller;
    opens com.example.test_mang.Controller to javafx.fxml;
}