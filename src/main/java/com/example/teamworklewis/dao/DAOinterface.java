package com.example.teamworklewis.dao;

import java.util.List;

public interface DAOinterface<T> {
    List<T> getAll();
    String add(List<T> object);
    T getObj(int id);
}
