package com.arithmos.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arithmos.rest.dao.UserDAO;
import com.arithmos.rest.model.User;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDAO userDAO;

	@Override
	public Optional<User> logEmployee(User employee) {
//		if(employee.getUsername().equals("a") && employee.getPassword().equals("a")){
//			User user = new User();
//			user.setUsername("a");
//			user.setRole("Admin");
//			return user;
//		}else return null;
//		System.out.println(userDAO.logEmployee(employee));
		return userDAO.logEmployee(employee);
	}

}
