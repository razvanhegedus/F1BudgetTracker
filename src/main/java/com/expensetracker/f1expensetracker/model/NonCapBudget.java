package com.expensetracker.f1expensetracker.model;

import java.util.ArrayList;
import java.util.List;

public class NonCapBudget implements IBudget {
    private int id;
    private final List<Expense> excludedSalaries;//2drivers, top three best paid workers
    private double value;


    public NonCapBudget(int id) {
        excludedSalaries = new ArrayList<>(5);
        value = 0;
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public void addExpense(Expense expense) {
        if(excludedSalaries.size() >= 5){
            throw new IllegalArgumentException("Can't add more than 5 excluded salaries.");
        }
        if(expense.getType().equals(ExpenseType.BUDGET_CAP) || expense.isIncludedInBudgetCap()) {
            throw new IllegalArgumentException("Can t add an capped expense to NonCapBudget");
        }
        excludedSalaries.add(expense);
        value += expense.getAmount();
    }

    @Override
    public void removeExpense(Expense expense) {
        excludedSalaries.remove(expense);
    }

    @Override
    public List<Expense> getExpenses() {
        return excludedSalaries;
    }

    @Override
    public void setid(int id) {
        this.id = id;
    }
}
