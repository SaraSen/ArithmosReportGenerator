package com.arithmos.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arithmos.rest.dao.ReportDAO;
import com.arithmos.rest.dao.UserDAO;
import com.arithmos.rest.model.User;

@RestController
@RequestMapping(path = "/pushreport")
public class ReportController {
	
	@Autowired
	private ReportDAO reportDAO;

//	write the method for api

}
