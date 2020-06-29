package com.arithmos.rest.service;

import com.arithmos.rest.model.User;

import java.util.Optional;

public interface UserService {
	
	public Optional<User> logEmployee(User employee);

}
