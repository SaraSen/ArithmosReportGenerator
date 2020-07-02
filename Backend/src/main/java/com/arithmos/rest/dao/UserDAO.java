package com.arithmos.rest.dao;

import com.arithmos.rest.model.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public Optional<User> logEmployee(User user) {

        try {

            Optional<User> userLoggedIn = jdbcTemplate.queryForObject(
                    "select * from user where Username = ? and Password = ?",
                    new Object[]{user.getUsername(), DigestUtils.md5Hex(user.getPassword())},
                    (rs, rowNum) ->
                            Optional.of(new User(
                                    rs.getString("Username"),
                                    rs.getString("role"),
                                    rs.getString("Password")

                            ))
            );

            
            if (userLoggedIn.get().getPassword().equals(DigestUtils.md5Hex(user.getPassword())) &&
                    userLoggedIn.get().getUsername().equals(user.getUsername())) {
                return userLoggedIn;
            } else return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
