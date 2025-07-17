package com.expensetracker.f1expensetracker.service;

import com.expensetracker.f1expensetracker.model.Expense;
import com.expensetracker.f1expensetracker.model.*;

import java.util.*;


public class TeamServiceImpl implements ITeamService {
    private final Map<String, Team> emailToTeam = new HashMap<>();

    @Override
    public boolean registerTeam(Team team) {
        if (team == null || team.getName() == null) {
            System.out.println("Invalid team name");
            return false;
        }

        if (!team.isValidEmail()) {
            System.out.println("Invalid email");
            return false;
        }

        if (!team.isValidPassword()) {
            System.out.println("Invalid password");
            return false;
        }

        if (emailToTeam.containsKey(team.getEmail())) {
            System.out.println("Email already registered");
            return false;
        }

        emailToTeam.put(team.getEmail(), team);
        return true;
    }

    @Override
    public Team login(String email, String password) {
        Team team = emailToTeam.get(email);
        return (team != null && team.getPassword().equals(password)) ? team : null;
    }

    @Override
    public void addExpense(Team team, Expense expense) {
        if (team == null || expense == null) {
            throw new IllegalArgumentException("Team and Expense cannot be null");
        }

        try {
            if (expense.getType() == ExpenseType.BUDGET_CAP) {
                team.getCapBudget().addExpense(expense);
                System.out.println("Cap expense added successfully");
            } else if (expense.getType() == ExpenseType.NON_CAP) {
                team.getNonCappedBudget().addExpense(expense);
                System.out.println("Non-cap expense added successfully");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Failed to add expense: " + e.getMessage());
        }
    }

    @Override
    public List<Expense> getExpenses(Team team) {
        if (team == null) return Collections.emptyList();

        List<Expense> allExpenses = new ArrayList<>();
        allExpenses.addAll(team.getCapBudget().getExpenses());
        allExpenses.addAll(team.getNonCappedBudget().getExpenses());
        return allExpenses;
    }
}
