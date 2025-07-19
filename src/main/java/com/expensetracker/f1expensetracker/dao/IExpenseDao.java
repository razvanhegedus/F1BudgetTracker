package com.expensetracker.f1expensetracker.dao;

import com.expensetracker.f1expensetracker.model.Expense;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface IExpenseDao {
    void addExpense(Expense expense) throws SQLException;
    Expense getExpenseById(int id) throws SQLException;
    List<Expense> getExpensesByBudgetId(int budgetId) throws SQLException;
    List<Expense> getExpensesByTeamId(int teamId) throws SQLException; // Across all budgets
    void updateExpense(Expense expense) throws SQLException;
    void deleteExpense(int id) throws SQLException;

    // Business Logic
    List<Expense> getExpensesByCategory(int budgetId, String category) throws SQLException;
    List<Expense> getExpensesByDateRange(int budgetId, LocalDate start, LocalDate end) throws SQLException;
    double getTotalExpensesForBudget(int budgetId) throws SQLException;
}
