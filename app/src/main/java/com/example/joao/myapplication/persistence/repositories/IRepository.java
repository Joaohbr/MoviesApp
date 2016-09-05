package com.example.joao.myapplication.persistence.repositories;

import java.util.Enumeration;

/**
 * Created by joao on 12/08/16.
 * A singleton repository interface
 */
public interface IRepository<TEntity> extends Iterable<TEntity> {
    /* Sugestão
    * implementar padrão Query Object para substituir este CRUD */

    boolean add(TEntity item);

    TEntity get(int position);

    boolean remove(int position);

    boolean remove(Object key);

    Enumeration<TEntity> getAll();

    int getItemCount();

    boolean contains(Object o);

    void clear();
}

