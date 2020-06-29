package com.arithmos.rest.dao;

import java.util.List;

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

	public List<Report> createTestData() {
		return jdbcTemplate.query("select * from task",
				(rs, rowNum) -> new Report(rs.getString("date"), rs.getString("Team"), rs.getString("Assignee"),
						rs.getString("JiraID"), rs.getString("Description"), rs.getString("Comments"),
						rs.getString("OnCall"), rs.getString("Delivery_date"), rs.getString("Status"),
						rs.getString("Blockers")));
	}

}
