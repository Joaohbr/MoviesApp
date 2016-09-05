package com.example.joao.myapplication.persistence.dao;

import com.example.joao.myapplication.domain.Movie;

import java.util.List;

/**
 * Created by joao on 26/08/16.
 */
public interface IDao<T> {

    void insert(T m);

    List<T> select(Object key);

    void remove(Object key);

}
