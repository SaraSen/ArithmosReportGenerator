package com.arithmos.rest.dao;

import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.arithmos.rest.db.DB;
import com.arithmos.rest.model.User;

import com.google.gson.JsonObject;

@Repository
public class UserDAO {
	
	public User logEmployee(User employee) {
		JsonObject success = new JsonObject();
		boolean isValidate=false;

//		try {
//			ResultSet rs = DB.search("SELECT * FROM `user` where `usernmae` = " + employee.getUsername()
//					+ " AND password =" + employee.getPassword() + "");
//			if (rs.next()) {
//				success.addProperty("verified", "true");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			success.addProperty("verified", "false");
//		}

		if (employee.getUsername().equals("a") && employee.getPassword().equals("a")) {
			User user = new User();
			user.setId(1);
			user.setRole("admin");
			user.setUsername("abc");
			return user;
		} else {
			return null;
		}

	}
}
