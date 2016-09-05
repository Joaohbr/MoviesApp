package com.example.joao.myapplication.persistence.repositories;

import com.example.joao.myapplication.domain.Movie;
import com.example.joao.myapplication.persistence.repositories.Repository;

/**
 * Created by joao on 22/08/16.
 */
public class MovieRepositorySearchResult extends Repository<Movie> {

    private Repository<Movie> mainRepository;

    public MovieRepositorySearchResult(Repository<Movie> mainRepository) {
        this.mainRepository = mainRepository;
    }


    @Override
    public boolean add(Movie item) {
        super.add(item);
        if( !mainRepository.contains(item) )
            mainRepository.add(item);
        return true;
    }

    @Override
    public boolean remove(int pos) {

        super.remove(pos);
        return mainRepository.remove(pos);
    }

    @Override
    public boolean remove(Object o) {

        super.remove(o);
        return mainRepository.remove(o);

    }
}
