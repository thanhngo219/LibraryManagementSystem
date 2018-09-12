package com.library.business;

import com.library.constant.LibraryConstant;
import com.library.dao.Dao;
import com.library.dao.DataAccess;
import com.library.dao.DataAccessFactory;
import com.library.dao.DataAccessSystem;

import java.util.ArrayList;
import java.util.List;

public class UserDao implements Dao<User> {

    List<User> users;

    public UserDao() {
        this.users = new ArrayList<>();
    }

    public boolean login(String username, String password) {
        Dao userDao = new UserDao();
        DataAccess da = DataAccessFactory.getDataAccess();
        da.readFile(userDao);
        List<User> users = userDao.getObjects();
        for (User user : users) {
            if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
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
