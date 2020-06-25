package com.arithmos.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arithmos.rest.dao.ReportDAO;
import com.arithmos.rest.dao.UserDAO;
import com.arithmos.rest.model.Report;
import com.arithmos.rest.model.User;
import com.google.gson.JsonObject;

@RestController
@RequestMapping(path = "/employees")
public class ReportController {

	@Autowired
	private ReportDAO reportDAO;

	@PostMapping(path = "/pushreport", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> pushReport(@RequestBody List<Report> report) throws Exception {
		return new ResponseEntity<String>(reportDAO.pushReport(report).toString(), HttpStatus.OK);

	}

}
