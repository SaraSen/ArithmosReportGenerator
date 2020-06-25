package com.arithmos.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.arithmos.rest.dao.UserDAO;
import com.arithmos.rest.model.User;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/employees")
public class UserController {

	@Autowired
	private UserDAO employeeDao;

	@PostMapping(path = "/login", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> logEmployee(@RequestBody User employee) throws Exception {
		try{
			System.out.println(employee);
			return new ResponseEntity<>(employeeDao.logEmployee(employee), HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
