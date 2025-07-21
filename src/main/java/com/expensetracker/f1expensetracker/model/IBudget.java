package com.expensetracker.f1expensetracker.model;

import java.util.List;

public interface IBudget {
    void addExpense(Expense expense);
    void removeExpense(Expense expense);
    List<Expense> getExpenses();
    void setid(int id);
}
