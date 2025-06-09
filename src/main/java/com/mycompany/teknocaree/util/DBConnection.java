package com.mycompany.teknocaree.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/teknocare";
    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Gagal memuat MySQL JDBC Driver. Pastikan file JAR ada di classpath.", e);
        } catch (SQLException e) {
            throw new SQLException("Gagal melakukan koneksi ke database: " + e.getMessage(), e);
        }
    }
}