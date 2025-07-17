package com.expensetracker.f1expensetracker.model;


import java.util.ArrayList;
import java.util.List;

public class CapBudget implements IBudget {
    private List<Expense> expenses;
    private final double cap;
    private double total;

    public CapBudget() {
        expenses = new ArrayList<Expense>();
        cap = 13500000;
        total = 0;
    }

    @Override
    public void addExpense(Expense expense) {
        if(expense.getAmount() + total <= cap && expense.getType().equals(ExpenseType.BUDGET_CAP)) {
            expenses.add(expense);
            total += expense.getAmount();
        }
        else {
            if(expense.getType().equals(ExpenseType.NON_CAP)){
                throw new IllegalArgumentException("Can't add a non cap expense to the capped budget");
            }
            throw new IllegalArgumentException("If you add this expense the budget cap is exceeded");
        }
    }

    @Override
    public void removeExpense(Expense expense) {
        if(expense.getType().equals(ExpenseType.NON_CAP)){
            throw new IllegalArgumentException("Can't remove a non cap expense to the capped budget");
        }
        expenses.remove(expense);
        total -= expense.getAmount();

    }

    @Override
    public List<Expense> getExpenses() {
        return expenses;
    }
}
