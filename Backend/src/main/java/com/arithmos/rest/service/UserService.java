package com.arithmos.rest.service;

import com.arithmos.rest.model.User;
import com.google.gson.JsonObject;

public interface UserService {
	
	public JsonObject logEmployee(User employee);

}
