package com.arithmos.rest.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.arithmos.rest.model.Report;

@Repository
public class ExcelDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

//	public List<Report> createTestData(){
//    	List<Report> reports = new ArrayList<Report>();
//    	try {
//    		//this
//			ResultSet rs = DB.search("SELECT * FROM `task");
//			while(rs.next()) {
//				reports.add(new Report(rs.getString("date"), rs.getString("Team"), rs.getString("Assignee"), rs.getString("JiraID"), rs.getString("Description"), rs.getString("Comments"),
//						rs.getString("OnCall"), rs.getString("Delivery_date"), rs.getString("Status"), rs.getString("Blockers")));
//			}
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//    	return reports;
//
//    }

	public List<Report> createTestData(Map<String, Date> dateRange) {

		formatDate(dateRange.get("startDate"));

		return jdbcTemplate.query(
				"SELECT * FROM task where date between '" + formatDate(dateRange.get("startDate")) + "' and '"
						+ formatDate(dateRange.get("endDate")) + "'",
				(rs, rowNum) -> new Report(rs.getString("date"), rs.getString("Team"), rs.getString("Assignee"),
						rs.getString("JiraID"), rs.getString("Description"), rs.getString("Comments"),
						rs.getString("OnCall"), rs.getString("Delivery_date"), rs.getString("Status"),
						rs.getString("Blockers")));
	}

	public String formatDate(Date retdate) {
		try {
			String dateStr = retdate.toString();
			DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
			Date date = (Date) formatter.parse(dateStr);
			System.out.println(date);

			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			String formatedDate = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-"
					+ cal.get(Calendar.DATE);
			System.out.println("formatedDate : " + formatedDate);

			return formatedDate;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

}
