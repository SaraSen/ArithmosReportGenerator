package com.arithmos.rest.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arithmos.rest.dao.ReportDAO;
import com.arithmos.rest.model.Report;
import com.google.gson.JsonObject;

@Service
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	ReportDAO reportDAO;

	@Override
	public JsonObject pushReport(List<Report> report) {
		return reportDAO.pushReport(report);
	}

	@Override
	public List<Report> getReport(Map<String, Date> dateMap) {
		return reportDAO.getReport(dateMap);
	}

	@Override
	public List<Report> getReportByTeam(String team, Date s, Date e) {
		return reportDAO.getReportByTeam(team,s,e);
	}
}
