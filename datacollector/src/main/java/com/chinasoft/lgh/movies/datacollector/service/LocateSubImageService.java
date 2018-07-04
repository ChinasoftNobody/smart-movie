package com.chinasoft.lgh.movies.datacollector.service;

import com.chinasoft.lgh.movies.datacollector.model.LocateSubImage;

public interface LocateSubImageService {

    /**
     * save sub image
     * @param locateSubImage sub image
     * @return success
     */
    boolean save(LocateSubImage locateSubImage);
}
