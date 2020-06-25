package com.arithmos.rest.dao;

import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.arithmos.rest.db.DB;
import com.arithmos.rest.model.Report;
import com.arithmos.rest.model.User;

import com.google.gson.JsonObject;

@Repository
public class ReportDAO {

	public JsonObject logEmployee(Report report) {
		JsonObject success = new JsonObject();

// implement the code

		if (!(report.getAssigneee().equals("") && report.getJiraID().equals(""))) {
			success.addProperty("here is the data", "some data");
		} else {
			success.addProperty("cannot get any data", "this is empty");
		}

		return success;

	}

}
