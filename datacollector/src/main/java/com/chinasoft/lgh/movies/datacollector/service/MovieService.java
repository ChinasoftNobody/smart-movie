package com.chinasoft.lgh.movies.datacollector.service;

import com.chinasoft.lgh.movies.datacollector.model.Movie;
import com.chinasoft.lgh.movies.datacollector.pojo.MovieFilter;

import java.util.List;

public interface MovieService {

    /**
     * save movies
     * @param movies movies
     */
    void batchSave(List<Movie> movies);

    /**
     * query by filter
     * @param movieFilter movie filter
     * @return movies
     */
    List<Movie> queryByFilter(MovieFilter movieFilter);
}
