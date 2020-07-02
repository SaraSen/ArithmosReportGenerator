package com.arithmos.rest.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.arithmos.rest.model.Report;
import com.google.gson.JsonObject;

public interface ReportService {
	
	public JsonObject pushReport(List<Report> report);
	public List<Report> getReport(Map<String, Date> dateMap);
	public List<Report> getReportByTeam(String team, Date s, Date e);
}
