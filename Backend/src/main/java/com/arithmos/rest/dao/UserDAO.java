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
