package com.arithmos.rest.dao;

import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.arithmos.rest.db.DB;
import com.arithmos.rest.model.User;

import com.google.gson.JsonObject;

@Repository
public class UserDAO {
	
	public JsonObject logEmployee(User employee) {
		JsonObject success = new JsonObject();

		try {
		ResultSet rs = DB.search("SELECT * FROM `User` where `Username` = " + employee.getUsername()
					+ " AND Password =" + employee.getPassword() + "");
			if (rs.next()) {
				success.addProperty("verified", "true");
			}
		} catch (Exception e) {
			e.printStackTrace();
			success.addProperty("verified", "false");
		}
		
		return success;

	}
}
