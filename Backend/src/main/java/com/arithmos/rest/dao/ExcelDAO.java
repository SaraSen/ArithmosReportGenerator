package com.arithmos.rest.dao;

import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.arithmos.rest.db.DB;
import com.arithmos.rest.model.Report;


@Repository
public class ExcelDAO {
	
	public List<Report> createTestData(){
    	List<Report> reports = new ArrayList<Report>();
    	try {
			ResultSet rs = DB.search("SELECT * FROM `task");
			while(rs.next()) {
				reports.add(new Report(rs.getString("date"), rs.getString("Team"), rs.getString("Assignee"), rs.getString("JiraID"), rs.getString("Description"), rs.getString("Comments"),
						rs.getString("OnCall"), rs.getString("Delivery_date"), rs.getString("Status"), rs.getString("Blockers")));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return reports;
    	
    }

}