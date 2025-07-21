package com.expensetracker.f1expensetracker.service;

import com.expensetracker.f1expensetracker.dao.ITeamDao;
import com.expensetracker.f1expensetracker.model.Expense;
import com.expensetracker.f1expensetracker.model.*;
import com.expensetracker.f1expensetracker.util.PasswordUtils;

import java.sql.SQLException;
import java.util.*;


public class TeamServiceImpl implements ITeamService {
    private final Map<String, Team> emailToTeam = new HashMap<>();
    private final ITeamDao teamDao;

    public TeamServiceImpl(ITeamDao teamDao) {
        this.teamDao = teamDao;
    }

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

        try {
            if (teamDao.getTeamByEmail(team.getEmail()) != null) {
                System.out.println("Email already registered");
                return false;
            }

            // Hash password before storing
            String hashed = PasswordUtils.hashPassword(team.getRawPassword());
            team.setHashedPassword(hashed);

            teamDao.addTeam(team);
            return true;

        } catch (SQLException e) {
            System.out.println("Registration failed: " + e.getMessage());
            return false;
        }
    }


    @Override
    public Team login(String email, String password) {
        try {
            Team team = teamDao.getTeamByEmail(email);
            if (team != null && PasswordUtils.verifyPassword(password, team.getRawPassword())) {
                return team;
            }
        } catch (SQLException e) {
            System.out.println("Login error: " + e.getMessage());
        }
        return null;
    }


    @Override
    public void addExpense(Team team, Expense expense) {
        if (team == null || expense == null) {
            throw new IllegalArgumentException("Team and Expense cannot be null");
        }

        try {
            if (expense.getType() == ExpenseType.BUDGET_CAP && expense.isIncludedInBudgetCap()) {
                team.getCapBudget().addExpense(expense);
                System.out.println("Cap expense added successfully");
            } else if (expense.getType() == ExpenseType.NON_CAP && !expense.isIncludedInBudgetCap()) {
                team.getNonCappedBudget().addExpense(expense);
                System.out.println("Non-cap expense added successfully");
            } else{
                System.out.println("Can't add this expense");
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
