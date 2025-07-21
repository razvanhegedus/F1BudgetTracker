package com.expensetracker.f1expensetracker.dao;

import com.expensetracker.f1expensetracker.model.Expense;
import com.expensetracker.f1expensetracker.model.ExpenseCategory;
import com.expensetracker.f1expensetracker.model.ExpenseType;
import com.expensetracker.f1expensetracker.util.PasswordUtils;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDaoImpl implements IExpenseDao{
    private static final String URL = "jdbc:sqlite:f1expensetracker.db";

    @Override
    public void addExpense(Expense expense) throws SQLException {
        String sql = "INSERT INTO expenses(budget_id, amount, description, expense_date, budget_type , category) VALUES (?, ?, ?, ?, ?, ?)";

        try(Connection conn = DriverManager.getConnection(URL);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, expense.getBudgetID());
            pstmt.setDouble(2, expense.getAmount());
            pstmt.setString(3, expense.getDescription());
            pstmt.setString(4, expense.getDate().toString());
            pstmt.setString(5, expense.getType().name());
            pstmt.setString(6, expense.getCategory().name());


            pstmt.executeUpdate();
            System.out.println("Expense added successfully");
        }catch(SQLException e){
            System.out.println("Failed to add expense in the table" + e.getMessage());
        }
    }

    @Override
    public Expense getExpenseById(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Expense> getExpensesByBudgetId(int budgetId) throws SQLException {
        List<Expense> expenses = new ArrayList<>();
        String sql = "SELECT * FROM expenses WHERE budget_id = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, budgetId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Expense expense = new Expense(
                        rs.getInt("id"),
                        rs.getString("description"),
                        rs.getDouble("amount"),
                        LocalDate.parse(rs.getString("expense_date")),
                        ExpenseType.valueOf(rs.getString("budget_type")),
                        ExpenseCategory.valueOf(rs.getString("category")),
                        budgetId
                );
                expense.setBudgetID(budgetId);
                expenses.add(expense);
            }
        }
        return expenses;
    }

    @Override
    public List<Expense> getExpensesByTeamId(int teamId) throws SQLException {
        return List.of();
    }

    @Override
    public void updateExpense(Expense expense) throws SQLException {

    }

    @Override
    public void deleteExpense(int id) throws SQLException {

    }

    @Override
    public List<Expense> getExpensesByCategory(int budgetId, ExpenseCategory category) throws SQLException {
        return List.of();
    }

    @Override
    public List<Expense> getExpensesByDateRange(int budgetId, LocalDate start, LocalDate end) throws SQLException {
        return List.of();
    }

    @Override
    public double getTotalExpensesForBudget(int budgetId) throws SQLException {
        String sql = "SELECT SUM(amount) as total FROM expenses WHERE budget_id = ?";

        try(Connection conn = DriverManager.getConnection(URL);
            PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setInt(1, budgetId);
            ResultSet rs = pstm.executeQuery();

            if(rs.next()){
                return rs.getDouble("total");
            }
            else{
                return 0.0;
            }
        }
    }
}
