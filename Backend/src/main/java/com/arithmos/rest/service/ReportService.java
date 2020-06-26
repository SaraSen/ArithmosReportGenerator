package com.arithmos.rest.service;

import java.util.List;

import com.arithmos.rest.model.Report;
import com.google.gson.JsonObject;

public interface ReportService {
	
	public JsonObject pushReport(List<Report> report);

}
