package com.chinasoft.lgh.movies.datacollector.service;

import com.chinasoft.lgh.movies.datacollector.model.LocateImage;

import javax.servlet.http.HttpServletResponse;

public interface LocateImageService {

    /**
     * save locate image
     * @param locateImage locateImage
     * @return success
     */
    boolean saveLocateImage(LocateImage locateImage);

    /**
     * query by id
     * @param id id
     * @return locate image
     */
    LocateImage queryById(String id);

    /**
     * write image to response
     * @param response response
     * @param locateImage image
     */
    void writeImage(HttpServletResponse response, LocateImage locateImage);
}
