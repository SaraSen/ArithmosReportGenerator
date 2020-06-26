package com.arithmos.rest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

<<<<<<< Updated upstream
		report.stream().forEach(c -> System.out.print(c.getAssignee()));
		success.addProperty("submitted", true);
=======
		try (Connection connection = DB.getMyConnection();
				// please insert your prepared statement
				PreparedStatement statement = connection.prepareStatement(
						"INSERT INTO `task`(`date`, `Team`, `Assignee`, `JiraID`, `Description`, `Comments`, `Oncall`, `Delivery_date`, `Status`, `Blockers`) VALUES (?,?,?,?,?,?,?,?,?,?)")) {
			int i = 0;
			int[] updateCounts;

			for (Report r : report) {
				statement.setString(1, r.getDate());
				statement.setString(2, r.getTeam());
				statement.setString(3, r.getAssignee());
				statement.setString(4, r.getJiraID());
				statement.setString(5, r.getTaskDesc());
				statement.setString(6, r.getComment());
				statement.setString(7, r.getOnCall());
				statement.setString(8, r.getDeliveryDate());
				statement.setString(9, r.getStatus());
				statement.setString(10, r.getBlockers());

				statement.addBatch();
				i++;

				if (i == report.size()) {
					updateCounts = statement.executeBatch();
					// =================================
					if (updateCounts.length == report.size()) {
						success.addProperty("submitted", true);
					} else {
						success.addProperty("submitted", false);
					}
					// ==============================
//					connection.commit();
//					statement.close();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

>>>>>>> Stashed changes
		return success;

	}

}
