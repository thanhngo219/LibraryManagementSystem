package com.library.dao;

import java.util.List;

public interface Dao<T> {
    String getUrl();

    List<T> getAll();

    void unpackData(Object data);

    List<T> getObjects();
}
