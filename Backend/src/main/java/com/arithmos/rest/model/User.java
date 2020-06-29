package com.arithmos.rest.model;

import org.springframework.jdbc.core.RowCallbackHandler;

public class User {

    public User() {

    }

    public User(String username, String role, String password) {
        super();
        this.id = id;
        this.password=password;
        this.username = username;
        this.role = role;
    }


 
    private Integer id;
    private String username;
    private String password;
    private String role;

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role + "]";
    }
}
