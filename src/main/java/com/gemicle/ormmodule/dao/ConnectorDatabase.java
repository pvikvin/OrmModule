package com.gemicle.ormmodule.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectorDatabase {
	private static final String URL = "jdbc:mysql://localhost:3306/GpsGenerator";
	private static final String USER = "root";
	private static final String PASSWORD = "1111";

	private static Connection con;
	private static Statement stmt;

	public ConnectorDatabase() {
		if (con == null || stmt == null) {
			connection();
		}
	}

	public void connection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return con;
	}

	public Statement getStatement() {
		return stmt;
	}

	public void close() {
		try {
			con.close();
			con = null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.close();
			stmt = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
