package com.arithmos.rest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.arithmos.rest.db.DB;
import com.arithmos.rest.model.Report;
import com.google.gson.JsonObject;

@Repository
public class ReportDAO {

	public JsonObject pushReport(List<Report> report) {

		
		JsonObject success = new JsonObject();

		report.stream().forEach(c -> System.out.print(c.getAssignee()));
		success.addProperty("submitted", true);
		return success;

	}

}
