package com.fabiok.sistemahospedaria.utils;

import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteConnector {
	public static void connect(){
		var url = "jdbc:sqlite:hospedaria.db";
		try(var conn = DriverManager.getConnection(url)){

		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}
}