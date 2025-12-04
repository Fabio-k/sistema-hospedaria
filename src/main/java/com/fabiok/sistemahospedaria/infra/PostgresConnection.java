package com.fabiok.sistemahospedaria.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConnection {
    private static String url = "jdbc:postgresql://localhost:5432/sistema_hospedaria";
    private static String user = "user";
    private static String password = "senha123";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
