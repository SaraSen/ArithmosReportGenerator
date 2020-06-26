package com.arithmos.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arithmos.rest.dao.UserDAO;
import com.arithmos.rest.model.User;
import com.google.gson.JsonObject;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDAO userDAO;

	@Override
	public JsonObject logEmployee(User employee) {
		return userDAO.logEmployee(employee);
	}

}
