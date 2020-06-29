package com.arithmos.rest.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class DB {
	private static Connection c;

	public static void createMyConnection() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		c = DriverManager.getConnection("jdbc:mysql://localhost:3306/report_generator?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true", "root", "");
	}

	public static void iud(String sql) throws Exception {
		if (c == null) {
			createMyConnection();
		}
		c.createStatement().executeUpdate(sql);
	}

	public static ResultSet search(String sql) throws Exception {
		if (c == null) {
			createMyConnection();
		}
		return c.createStatement().executeQuery(sql);

	}

	public static Connection getMyConnection() throws Exception {
		if (c == null) {
			createMyConnection();
		}
		return c;
	}
}
