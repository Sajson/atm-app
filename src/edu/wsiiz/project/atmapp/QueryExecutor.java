package edu.wsiiz.project.atmapp;

import java.sql.*;

public class QueryExecutor {
    private final Connection connection;

    public QueryExecutor() {
        this.connection = DBConnector.connect();
    }

    public ResultSet executeQuery(String query) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    public void executeUpdate(String query) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
    }
    public void close() throws SQLException {
        this.connection.close();
    }
}