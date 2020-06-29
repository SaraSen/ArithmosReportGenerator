package com.arithmos.rest.dao;

import com.arithmos.rest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;
import java.util.Optional;

@Repository
public class UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

//	public JsonObject logEmployee(User employee) {
//
//		JsonObject success = new JsonObject();
//		boolean value = false;
//
//		try {
//			String sql = "SELECT * FROM `user` WHERE `Username`= '" + employee.getUsername() + "' AND `Password` = '"
//					+ employee.getPassword() + "' ";
//			ResultSet rs = DB.search(sql);
//			if (rs.next()) {
//				success.addProperty("verified", "true");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			success.addProperty("verified", "false");
//
//		}
//
//		return success;
//	}

    public Optional<User> logEmployee(User user) {

        try {

            Optional<User> userLoggedIn = jdbcTemplate.queryForObject(
                    "select * from user where Username = ? and Password = ?",
                    new Object[]{user.getUsername(), user.getPassword()},
                    (rs, rowNum) ->
                            Optional.of(new User(
                                    rs.getString("Username"),
                                    rs.getString("role"),
                                    rs.getString("Password")

                            ))
            );
            if (userLoggedIn.get().getPassword().equals(user.getPassword()) &&
                    userLoggedIn.get().getUsername().equals(user.getUsername())) {
                return userLoggedIn;
            } else return null;

        } catch (Exception e) {
            return null;
        }

    }

}
