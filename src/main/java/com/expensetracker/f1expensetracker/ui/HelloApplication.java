package com.expensetracker.f1expensetracker.ui;

import com.expensetracker.f1expensetracker.dao.ExpenseDaoImpl;
import com.expensetracker.f1expensetracker.model.Expense;
import com.expensetracker.f1expensetracker.util.DBInitializer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class HelloApplication extends Application {
    private final ExpenseDaoImpl expenseDao = new ExpenseDaoImpl(); // DAO instance

    @Override
    public void start(Stage primaryStage) throws SQLException {
        TableView<Expense> tableView = new TableView<>();

        // Define columns based on Expense model
        TableColumn<Expense, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Expense, String> descCol = new TableColumn<>("Description");
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Expense, Double> amountCol = new TableColumn<>("Amount");
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));

        TableColumn<Expense, String> categoryCol = new TableColumn<>("Category");
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category")); // Make sure getter exists

        // Add columns
        tableView.getColumns().addAll(idCol, descCol, amountCol, categoryCol);

        // Load data
        List<Expense> expenses = expenseDao.getExpensesByBudgetId(1); // Implement this in your DAO
        tableView.getItems().addAll(expenses);

        BorderPane root = new BorderPane(tableView);
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Expense Tracker");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }
}