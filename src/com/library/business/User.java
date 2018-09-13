package com.library.business;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 9L;

    private String userName;

    private String password;

    private Role role;

    public User(String userName, String password, Role role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }
}
