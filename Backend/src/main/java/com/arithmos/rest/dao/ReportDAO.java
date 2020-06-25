package com.arithmos.rest.dao;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.arithmos.rest.db.DB;
import com.arithmos.rest.model.Report;
import com.arithmos.rest.model.User;

import com.google.gson.JsonObject;

@Repository
public class ReportDAO {
	

	public JsonObject pushReport(List<Report> report) {
		JsonObject success = new JsonObject();
		success.addProperty("submitted", true);
		return success;

	}

}
