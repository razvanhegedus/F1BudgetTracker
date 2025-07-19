 module com.expensetracker.f1expensetracker {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
     requires jdk.unsupported;
     requires java.sql;

     opens com.expensetracker.f1expensetracker to javafx.fxml;
    exports com.expensetracker.f1expensetracker;
}