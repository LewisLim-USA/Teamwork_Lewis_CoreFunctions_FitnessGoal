package com.example.teamworklewis.dao;

import java.util.List;

public class DSAImpObj<T> implements DAOinterface<T>{


    @Override
    public List<T> getAll() {
        return List.of();
    }

    @Override
    public String findById(List<T> object) {
        return "";
    }

    @Override
    public T add(T id) {
        return null;
    }
}
