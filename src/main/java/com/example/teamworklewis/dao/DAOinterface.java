package com.example.teamworklewis.dao;

import java.util.List;

public interface DAOinterface<T>{

    List<T> getAll();
    String findById (List<T> object);
    T add (T id);
}
