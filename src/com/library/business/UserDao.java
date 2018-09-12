package com.library.business;

import com.library.constant.LibraryConstant;
import com.library.dao.Dao;

import java.util.ArrayList;
import java.util.List;

public class UserDao implements Dao<User> {

    List<User> users;

    public UserDao() {
        this.users = new ArrayList<>();
    }

    @Override
    public String getUrl() {
        return LibraryConstant.USER_DIR;
    }

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public void unpackData(Object data) {
        this.users = (List<User>) data;
    }

    @Override
    public List<User> getObjects() {
        return users;
    }
}
