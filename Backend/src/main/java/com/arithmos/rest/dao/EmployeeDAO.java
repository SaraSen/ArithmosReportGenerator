package com.arithmos.rest.dao;

import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.arithmos.rest.db.DB;
import com.arithmos.rest.model.Employee;

import com.google.gson.JsonObject;

@Repository
public class EmployeeDAO {
	
	public JsonObject logEmployee(Employee employee) {
		JsonObject success = new JsonObject();

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

		if (!(employee.getUsername().equals("") && employee.getPassword().equals(""))) {
			success.addProperty("verified", true);
		} else {
			success.addProperty("verified", false);
		}

		return success;

	}
}
