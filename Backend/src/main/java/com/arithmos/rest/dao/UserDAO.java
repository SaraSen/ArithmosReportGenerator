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
			String sql = "SELECT * FROM `user` WHERE `Username`= '" + employee.getUsername() + "' AND `Password` = '"
					+ employee.getPassword() + "' ";
			System.out.println(sql);
			ResultSet rs = DB.search(sql);
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
