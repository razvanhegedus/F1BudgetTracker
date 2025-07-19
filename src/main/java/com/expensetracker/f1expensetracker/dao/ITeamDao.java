package com.expensetracker.f1expensetracker.dao;

import com.expensetracker.f1expensetracker.model.Team;

import java.sql.SQLException;


public interface ITeamDao {
    void save(Team team);
    void addTeam(Team team) throws SQLException;
    Team getTeamById(int id) throws SQLException;
    Team getTeamByEmail(String email) throws SQLException; // For login/validation
    void updateTeam(Team team) throws SQLException;
    void deleteTeam(int id) throws SQLException;

    // Business Logic
    boolean validateCredentials(String email, String password) throws SQLException;
}
