package com.expensetracker.f1expensetracker.dao;

import com.expensetracker.f1expensetracker.model.CapBudget;
import com.expensetracker.f1expensetracker.model.Expense;
import com.expensetracker.f1expensetracker.model.IBudget;
import com.expensetracker.f1expensetracker.model.NonCapBudget;

import java.sql.*;
import java.util.List;

public class BudgetDaoImpl implements IBudgetDAO{
    private static final String URL = "jdbc:sqlite:f1expensetracker.db";



    @Override
    public void addBudget(String type, int team_id, IBudget budget) throws SQLException{
        if (!"CAP".equals(type) && !"NON_CAP".equals(type)) {
            throw new IllegalArgumentException("Budget type must be 'CAP' or 'NON_CAP'");
        }

        String sql = "INSERT INTO budgetss(team_id, type) VALUES (?, ?)";

        try(Connection conn = DriverManager.getConnection(URL);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, team_id );
            pstmt.setString(2, type);
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating budget failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    budget.setid(generatedId);  // Set the generated ID back to the budget object
                } else {
                    throw new SQLException("Creating budget failed, no ID obtained.");
                }
            }
        }
    }

    @Override
    public IBudget getBudgetById(int id) throws SQLException {
        return null;
    }

    @Override
    public void updateBudget(IBudget budget) throws SQLException {

    }

    @Override
    public void deleteBudget(int id) throws SQLException {

    }

    public IBudget getCapBudgetForTeam(int teamId) throws SQLException {
        String sql = "SELECT id FROM budgets WHERE team_id = ? AND type = 'CAP'";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, teamId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int budgetId = rs.getInt("id");

                CapBudget capBudget = new CapBudget(budgetId);

                // Fetch and add expenses
                ExpenseDaoImpl expenseDao = new ExpenseDaoImpl();
                List<Expense> expenses = expenseDao.getExpensesByBudgetId(budgetId);
                for (Expense e : expenses) {
                    capBudget.addExpense(e);
                }

                return capBudget;
            } else {
                return null;
            }
        }
    }


    @Override
    public IBudget getNonCapBudgetForTeam(int teamId) throws SQLException {
        String sql = "SELECT id FROM budgets WHERE team_id = ? AND type = 'NON_CAP'";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, teamId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int budgetId = rs.getInt("id");

                NonCapBudget Budget = new NonCapBudget(budgetId);

                // Fetch and add expenses
                ExpenseDaoImpl expenseDao = new ExpenseDaoImpl();
                List<Expense> expenses = expenseDao.getExpensesByBudgetId(budgetId);
                for (Expense e : expenses) {
                    Budget.addExpense(e);
                }

                return Budget;
            } else {
                return null;
            }
        }
    }

    @Override
    public double getTotalSpent(IBudget budget) throws SQLException {
        return 0;
    }

    @Override
    public double getRemainingBudget(IBudget budget) throws SQLException {
        return 0;
    }

    @Override
    public List<Expense> getExpensesForBudget(IBudget budget) throws SQLException {
        return List.of();
    }

    @Override
    public void addExpenseToBudget(IBudget budget, Expense expense) throws SQLException {

    }


}
