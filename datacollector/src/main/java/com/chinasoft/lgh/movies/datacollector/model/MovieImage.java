package com.chinasoft.lgh.movies.datacollector.model;

import java.util.UUID;

public class MovieImage {

    private String id;
    private String movieId;
    private String image;
    private int index;

    public MovieImage() {
    }

    public MovieImage(String id, String src, int index) {
        this.id = UUID.randomUUID().toString();
        this.movieId = id;
        this.image = src;
        this.index = index;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
