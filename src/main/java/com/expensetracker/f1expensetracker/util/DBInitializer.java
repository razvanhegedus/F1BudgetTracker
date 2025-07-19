package com.expensetracker.f1expensetracker.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DBInitializer {
    private static final String URL = "jdbc:sqlite:f1expensetracker.db";

    public static void initialiseDataBase() {
        try(Connection conn = DriverManager.getConnection(URL);
            Statement stmt = conn.createStatement()) {

            stmt.execute("PRAGMA foreign_keys = ON;");

            String createTeamTable = "CREATE TABLE IF NOT EXISTS teams (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT NOT NULL," +
                    "email TEXT UNIQUE NOT NULL," +
                    "password TEXT NOT NULL" +
                    ");";

            String createBudgetTable = "CREATE TABLE IF NOT EXISTS budgets (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "team_id INTEGER NOT NULL," +
                    "type TEXT NOT NULL CHECK(type IN ('CAP', 'NON_CAP'))," +
                    "FOREIGN KEY (team_id) REFERENCES teams(id) ON DELETE CASCADE," +
                    "UNIQUE (team_id, type)" +
                    ");";

            String createExpenseTable = "CREATE TABLE IF NOT EXISTS expenses (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "budget_id INTEGER NOT NULL," +
                    "amount REAL NOT NULL CHECK(amount >= 0)," +
                    "description TEXT," +
                    "expense_date TEXT NOT NULL," +
                    "category TEXT," +
                    "FOREIGN KEY (budget_id) REFERENCES budgets(id) ON DELETE CASCADE" +
                    ");";

            stmt.execute(createTeamTable);
            stmt.execute(createBudgetTable);
            stmt.execute(createExpenseTable);

            System.out.println("Database tables created or verified successfully.");

        }catch(SQLException e){
            System.out.println("Failed to initialize the tables" + e.getMessage());
        }
    }

}
