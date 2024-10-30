package com.practice.swd392_demo.repository;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Repository {
    final String USER_NAME="sa";
    final String PASSWORD="sa";
    final String URL = "jdbc:sqlserver://localhost:8080;databasename=";
    protected Connection connection;
    protected PreparedStatement ps;
    protected ResultSet resultSet;
    protected String query;
    public Repository(){
        try {
            connection=getConnection();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Connection getConnection() throws ClassNotFoundException, SQLException{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(URL,USER_NAME,PASSWORD);
    }
    public void closeConnection() {
        try {
            if (resultSet != null) resultSet.close();
            if (ps != null) ps.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
