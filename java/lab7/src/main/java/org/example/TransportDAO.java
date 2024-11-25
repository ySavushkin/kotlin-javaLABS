package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//В кожній ДАОшці реалізовано круд функціонал
public class TransportDAO {

    private Connection connection;
    
    public TransportDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    public void addTransport(String type, String brand, int capacity) throws SQLException {
        String sql = "INSERT INTO Transport (type, brand, capacity) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, type);
            statement.setString(2, brand);
            statement.setInt(3, capacity);
            statement.executeUpdate();
        }
    }

    public List<String> getAllTransport() throws SQLException {
        String sql = "SELECT * FROM Transport";
        List<String> transports = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                transports.add("ID: " + resultSet.getInt("id") +
                        ", Type: " + resultSet.getString("type") +
                        ", Brand: " + resultSet.getString("brand") +
                        ", Capacity: " + resultSet.getInt("capacity"));
            }
        }
        return transports;
    }

    public void updateTransport(int id, String newType, String newBrand, int newCapacity) throws SQLException {
        String sql = "UPDATE Transport SET type = ?, brand = ?, capacity = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newType);
            statement.setString(2, newBrand);
            statement.setInt(3, newCapacity);
            statement.setInt(4, id);
            statement.executeUpdate();
        }
    }

    public void deleteTransport(int id) throws SQLException {
        String sql = "DELETE FROM Transport WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
