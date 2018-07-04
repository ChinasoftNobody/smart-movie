package com.chinasoft.lgh.movies.datacollector.service;

import com.chinasoft.lgh.movies.datacollector.model.MovieImage;

import java.util.List;

public interface MovieImageService {
    /**
     * save image batch
     * @param subImage images
     */
    void batchSave(List<MovieImage> subImage);

    /**
     * get sub images by id
     * @param id id
     * @return sub images
     */
    List<String> getById(String id);

    /**
     * query not located
     * @return movieList
     */
    List<MovieImage> queryNotLocated();
}
