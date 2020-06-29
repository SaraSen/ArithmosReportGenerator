package com.arithmos.rest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.arithmos.rest.db.DB;
import com.arithmos.rest.model.Report;
import com.google.gson.JsonObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@Repository
public class ReportDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JsonObject pushReport(List<Report> book) {
		JsonObject success = new JsonObject();

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.now();

		for (Report r : book) {
			jdbcTemplate.update(
					"INSERT INTO `task`(`date`, `Team`, `Assignee`, `JiraID`, `Description`, `Comments`, `Oncall`, `Delivery_date`, `Status`, `Blockers`) VALUES (?,?,?,?,?,?,?,?,?,?)",
					dtf.format(localDate), r.getTeam(), r.getAssignee(), r.getJiraID(),r.getTaskDesc(),r.getComment(),r.getOnCall(),r.getDeliveryDate().split("T")[0],r.getStatus(),r.getBlockers());
		}
		
		success.addProperty("submitted", true);
		return success;
	}

//	public JsonObject pushReportr(List<Report> report) {
//		JsonObject success = new JsonObject();
//
//		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		LocalDate localDate = LocalDate.now();
//
//		try (Connection connection = DB.getMyConnection();
//
//				PreparedStatement statement = connection.prepareStatement(
//						"INSERT INTO `task`(`date`, `Team`, `Assignee`, `JiraID`, `Description`, `Comments`, `Oncall`, `Delivery_date`, `Status`, `Blockers`) VALUES (?,?,?,?,?,?,?,?,?,?)")) {
//			int i = 0;
//			int[] updateCounts;
//
//			for (Report r : report) {
//				statement.setString(1, dtf.format(localDate));
//				statement.setString(2, r.getTeam());
//				statement.setString(3, r.getAssignee());
//				statement.setString(4, r.getJiraID());
//				statement.setString(5, r.getTaskDesc());
//				statement.setString(6, r.getComment());
//				statement.setString(7, r.getOnCall());
//				statement.setString(8, r.getDeliveryDate().split("T")[0]);
//				statement.setString(9, r.getStatus());
//				statement.setString(10, r.getBlockers());
//
//				statement.addBatch();
//				i++;
//
//				if (i == report.size()) {
//					updateCounts = statement.executeBatch();
//					// =================================
//					if (updateCounts.length == report.size()) {
//						success.addProperty("submitted", true);
//					} else {
//						success.addProperty("submitted", false);
//					}
//					// ==============================
//
//				}
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
//
//		return success;
//
//	}

}
