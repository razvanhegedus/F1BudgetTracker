package com.expensetracker.f1expensetracker.model;

import java.util.List;

public class Team {
    private int id;
    private String name;
    private String email;
    private String passwordHash;
    private List<Expense> expenses;
    private IBudget capBudget;
    private IBudget nonCappedBudget;

    public Team(int id, String name, String email, String passwordHash, IBudget capBudget, IBudget nonCappedBudget) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.capBudget = capBudget;
        this.nonCappedBudget = nonCappedBudget;
        this.expenses = capBudget.getExpenses();
        this.expenses.addAll(nonCappedBudget.getExpenses());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public IBudget getCapBudget() {
        return capBudget;
    }

    public IBudget getNonCappedBudget() {
        return nonCappedBudget;
    }
}
