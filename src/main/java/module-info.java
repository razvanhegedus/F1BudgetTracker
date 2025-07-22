module com.expensetracker.f1expensetracker {
    // JavaFX core modules
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    // 3rd party UI libraries
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    // Java core and DB
    requires java.sql;
    requires jbcrypt;
    requires jdk.unsupported;

    // Open packages for FXML reflection (controllers, etc.)
    opens com.expensetracker.f1expensetracker.ui to javafx.fxml;

    // Optional if using JavaFX properties (ObservableList etc.)
    opens com.expensetracker.f1expensetracker.model to javafx.base;

    // Export all top-level packages
    exports com.expensetracker.f1expensetracker.ui;
    exports com.expensetracker.f1expensetracker.model;
    exports com.expensetracker.f1expensetracker.service;
    exports com.expensetracker.f1expensetracker.util;
}
