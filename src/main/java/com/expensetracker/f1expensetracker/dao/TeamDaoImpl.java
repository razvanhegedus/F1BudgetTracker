package com.expensetracker.f1expensetracker.dao;

import com.expensetracker.f1expensetracker.model.Team;
import com.expensetracker.f1expensetracker.util.PasswordUtils;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

public class TeamDaoImpl implements ITeamDao {
    private static final String URL = "jdbc:sqlite:f1expensetracker.db";


    @Override
    public void save(Team team) {

    }

    @Override
    public void addTeam(Team team) throws SQLException {
        String sql = "INSERT INTO teams(name, email, password) VALUES (?, ?, ?)";

        try(Connection conn = DriverManager.getConnection(URL);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, team.getName());
            pstmt.setString(2, team.getEmail());

            String hashedPassword = PasswordUtils.hashPassword(team.getRawPassword());
            pstmt.setString(3, hashedPassword);

            pstmt.executeUpdate();
        }catch(SQLException e){
            System.out.println("Failed to add team in the table" + e.getMessage());
        }
    }

    @Override
    public Team getTeamById(int id) throws SQLException {
        return null;
    }

    @Override
    public Team getTeamByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM teams WHERE email = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            /*
            if (rs.next()) {
                Team team = new Team();
                team.setId(rs.getInt("id"));
                team.setName(rs.getString("name"));
                team.setEmail(rs.getString("email"));
                team.setPassword(rs.getString("password"));
                // Optionally initialize budgets if needed
                return team;
            }*/
            return null; // Team not found
        }
    }

    @Override
    public void updateTeam(Team team) throws SQLException {

    }

    @Override
    public void deleteTeam(int id) throws SQLException {

    }

    @Override
    public boolean validateCredentials(String email, String password) throws SQLException {
        String sql = "SELECT COUNT(*) FROM teams WHERE email = ? AND password = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedHash = rs.getString("password");
                return BCrypt.checkpw(password, storedHash);
            }
        }
        return false;
    }
}
