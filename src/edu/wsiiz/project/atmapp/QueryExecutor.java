package edu.wsiiz.project.atmapp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryExecutor {
    private final Connection connection;

    public QueryExecutor() throws SQLException {
        this.connection = DBConnector.connect();
    }

    public ResultSet executeQuery(String query) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    public int executeUpdate(String query) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeUpdate(query);
    }

    public void close() throws SQLException {
        this.connection.close();
    }

}