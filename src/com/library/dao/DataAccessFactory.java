package com.library.dao;

public final class DataAccessFactory {

    public static DataAccess getDataAccess() {
        return new DataAccessSystem();
    }
}
