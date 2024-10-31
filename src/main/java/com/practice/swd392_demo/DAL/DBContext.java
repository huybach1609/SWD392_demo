package com.practice.swd392_demo.DAL;

import com.practice.swd392_demo.models.User;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBContext {

    /*Change/update information of your database connection, DO NOT change name of instance variables in this class*/
    private String serverName = "localhost";
    private String portNumber = "1433";
    private String dbName = "SWD_demo";
    private String useId = "sa";
    private String pass = "sa";

    /*DO NOT EDIT THE BELOW METHOD, YOU MUST USE ONLY THIS ONE FOR YOUR DATABASE CONNECTION*/
    public Connection getConnection() throws Exception {
        String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName + ";trustServerCertificate=true";

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, useId, pass);
    }

    public static void main(String[] args) {
        try {
//            System.out.println(new DBContext().getConnection());
//            System.out.println(DepartmentDAO.ins.getList().get(0));
            System.out.println(AccountDAO.ins.getByUID(2));
        } catch (Exception e) {

        }
    }
}
