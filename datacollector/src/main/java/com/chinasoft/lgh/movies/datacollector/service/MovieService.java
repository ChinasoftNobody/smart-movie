package com.chinasoft.lgh.movies.datacollector.service;

import com.chinasoft.lgh.movies.datacollector.model.Movie;
import com.chinasoft.lgh.movies.datacollector.pojo.MovieFilter;
import com.chinasoft.lgh.movies.datacollector.pojo.Pages;

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
    Pages<Movie> queryByFilter(MovieFilter movieFilter);

    /**
     * query types
     * @return type list
     */
    List<String> queryTypes();

    /**
     * query regions
     * @return regions
     */
    List<String> queryRegions();

    /**
     * query movie by ID
     * @param id id
     * @return movie info
     */
    Movie queryById(String id);
}
