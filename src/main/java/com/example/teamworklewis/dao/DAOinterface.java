package com.example.teamworklewis.dao;

import java.util.List;

public interface DAOInterface<T> {
    List<T> getAll();
    String add(List<T> object);
    T getObj(int id);
}
