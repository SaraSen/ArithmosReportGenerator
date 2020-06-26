package com.arithmos.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arithmos.rest.dao.ReportDAO;
import com.arithmos.rest.model.Report;
import com.google.gson.JsonObject;

@Service
public class ReporServiceImpl implements ReportService {
	
	@Autowired
	ReportDAO reportDAO;

	@Override
	public JsonObject pushReport(List<Report> report) {
		return reportDAO.pushReport(report);
	}

}
