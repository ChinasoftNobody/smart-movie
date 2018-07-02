package com.chinasoft.lgh.movies.datacollector.mapper;

import com.chinasoft.lgh.movies.datacollector.model.LocateImage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LocateImageMapper {

    /**
     * save locate image
     * @param locateImage locateImage
     * @return success
     */
    boolean saveLocateImage(@Param("locateImage")LocateImage locateImage);

    /**
     * query by id
     * @param id id
     * @return locate image
     */
    LocateImage queryById(@Param("id") String id);
}
