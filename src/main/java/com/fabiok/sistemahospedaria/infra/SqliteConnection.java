package com.fabiok.sistemahospedaria.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteConnection {
    private static String url = "jdbc:sqlite:hospedaria.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url);
    }
}
