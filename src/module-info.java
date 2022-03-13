module proveHMS3project {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires java.desktop;
    requires javafx.web;
    requires java.sql.rowset;
    requires mysql.connector.java;


    opens FrontEnd;
    opens sample;
}