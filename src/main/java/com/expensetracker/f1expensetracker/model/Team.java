package com.expensetracker.f1expensetracker.model;

import java.util.List;
import java.util.regex.Pattern;

public class Team {
    private int id;
    private String name;
    private String email;
    private String password;
    private List<Expense> expenses;
    private IBudget capBudget;
    private IBudget nonCappedBudget;

    public Team(int id, String name, String email, String password, IBudget capBudget, IBudget nonCappedBudget) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public boolean isValidEmail() {

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern p = Pattern.compile(emailRegex);

        return this.email != null && p.matcher(this.email).matches();
    }

    public boolean isValidPassword() {
        String regex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z0-9]).{7,}$";
        Pattern p = Pattern.compile(regex);

        return this.password != null && p.matcher(this.password).matches();
    }
    
}
