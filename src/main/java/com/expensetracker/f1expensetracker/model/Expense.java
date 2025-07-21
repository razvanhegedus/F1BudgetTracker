package com.expensetracker.f1expensetracker.model;

import java.time.LocalDate;

public class Expense {
    private int id;
    private String description;
    private double amount;
    private LocalDate date;
    private ExpenseType type;
    private ExpenseCategory category;
    private int budgetID;

    public Expense(int id, String description, double amount, LocalDate date, ExpenseType type, ExpenseCategory category, int budgetID) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.type = type;
        this.category = category;
        this.budgetID = budgetID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ExpenseType getType() {
        return type;
    }

    public void setType(ExpenseType type) {
        this.type = type;
    }

    public ExpenseCategory getCategory() {
        return category;
    }

    public void setCategory(ExpenseCategory category) {
        this.category = category;
    }

    public boolean isIncludedInBudgetCap() {
        return category != ExpenseCategory.DRIVER_SALARY &&
                category != ExpenseCategory.EXCLUDED_STAFF_SALARY;
    }

    public int getBudgetID() {
        return budgetID;
    }

    public void setBudgetID(int budgetID) {
        this.budgetID = budgetID;
    }
}
