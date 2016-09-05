package com.example.joao.myapplication.persistence.repositories;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;

/**
 * Created by joao on 22/08/16.
 */
public abstract class Repository<T> implements IRepository<T> {

    protected ArrayList<T> lista = new ArrayList<>();

    @Override
    public boolean add(T item) {
        return lista.add(item);
    }

    @Override
    public boolean remove(int position) {
        if( lista.remove(position) != null)
            return true;
        else
            return false;

    }

    @Override
    public boolean remove(Object o) { return lista.remove(o); }

    @Override
    public T get(int position) {
        return lista.get(position);
    }

    @Override
    public Enumeration<T> getAll() {
        return null;
    }

    @Override
    public int getItemCount() { return lista.size(); }

    @Override
    public Iterator<T> iterator() {
        return lista.iterator();
    }

    @Override
    public boolean contains(Object o) { return lista.contains(o); }

    @Override
    public void clear() { lista.clear(); }
}
