package com.arithmos.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.arithmos.rest.dao.EmployeeDAO;
import com.arithmos.rest.model.Employee;
import com.google.gson.JsonObject;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {
	@Autowired
	private EmployeeDAO employeeDao;





	@PostMapping(path = "/login", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> logEmployee(@RequestBody Employee employee) throws Exception {
		return new ResponseEntity<String>(employeeDao.logEmployee(employee).toString(), HttpStatus.OK);
				
	}
}
