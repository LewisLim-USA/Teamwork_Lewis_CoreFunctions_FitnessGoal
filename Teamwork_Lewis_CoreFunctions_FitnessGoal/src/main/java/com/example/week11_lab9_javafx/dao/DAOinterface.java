package com.example.week11_lab9_javafx.dao;

import java.util.List;

public interface DAOinterface<T>{

    List<T> getAll();
    String findById (List<T> object);
    T add (T id);
}
