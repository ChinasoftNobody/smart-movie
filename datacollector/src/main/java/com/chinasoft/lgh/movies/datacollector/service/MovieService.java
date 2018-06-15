package com.chinasoft.lgh.movies.datacollector.service;

import com.chinasoft.lgh.movies.datacollector.model.Movie;

import java.util.List;

public interface MovieService {

    /**
     * save movies
     * @param movies movies
     */
    void batchSave(List<Movie> movies);
}
