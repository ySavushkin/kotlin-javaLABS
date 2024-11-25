package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//В кожній ДАОшці реалізовано круд функціонал
public class RoutesDAO {

    private Connection connection;

    public RoutesDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    public void addRoute(String name, double distance) throws SQLException {
        String sql = "INSERT INTO Routes (name, distance) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setDouble(2, distance);
            statement.executeUpdate();
        }
    }

    public List<String> getAllRoutes() throws SQLException {
        String sql = "SELECT * FROM Routes";
        List<String> routes = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                routes.add("ID: " + resultSet.getInt("id") +
                        ", Name: " + resultSet.getString("name") +
                        ", Distance: " + resultSet.getDouble("distance"));
            }
        }
        return routes;
    }

    public String getRouteById(int id) throws SQLException {
        String sql = "SELECT * FROM Routes WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return "ID: " + resultSet.getInt("id") +
                            ", Name: " + resultSet.getString("name") +
                            ", Distance: " + resultSet.getDouble("distance");
                }
            }
        }
        return "Route with ID " + id + " not found.";
    }

    public void updateRoute(int id, String newName, double newDistance) throws SQLException {
        String sql = "UPDATE Routes SET name = ?, distance = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newName);
            statement.setDouble(2, newDistance);
            statement.setInt(3, id);
            statement.executeUpdate();
        }
    }

    public void deleteRoute(int id) throws SQLException {
        String sql = "DELETE FROM Routes WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
