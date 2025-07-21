package com.expensetracker.f1expensetracker.dao;

import com.expensetracker.f1expensetracker.model.Expense;
import com.expensetracker.f1expensetracker.model.IBudget;

import java.sql.SQLException;
import java.util.List;

public interface IBudgetDAO {
    // CRUD Operations
    void addBudget(String type, int team_id, IBudget budget) throws SQLException;
    IBudget getBudgetById(int id) throws SQLException;
    void updateBudget(IBudget budget) throws SQLException;
    void deleteBudget(int id) throws SQLException;

    // Team-Specific Budget Access
    IBudget getCapBudgetForTeam(int teamId) throws SQLException;
    IBudget getNonCapBudgetForTeam(int teamId) throws SQLException;

    // Budget Analysis
    double getTotalSpent(IBudget budget) throws SQLException;
    double getRemainingBudget(IBudget budget) throws SQLException;

    // Expense Management
    List<Expense> getExpensesForBudget(IBudget budget) throws SQLException;
    void addExpenseToBudget(IBudget budget, Expense expense) throws SQLException;
}
