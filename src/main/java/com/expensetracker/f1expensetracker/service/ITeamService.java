package com.expensetracker.f1expensetracker.service;

import com.expensetracker.f1expensetracker.model.Expense;
import com.expensetracker.f1expensetracker.model.Team;

import java.util.List;

public interface ITeamService {
    boolean registerTeam(Team team) throws Exception;
    Team login(String email, String password) throws Exception;
    void addExpense(Team team, Expense expense) throws Exception;
    List<Expense> getExpenses(Team team);
}
