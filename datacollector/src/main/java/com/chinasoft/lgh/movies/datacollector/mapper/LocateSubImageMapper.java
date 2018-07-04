package com.chinasoft.lgh.movies.datacollector.mapper;

import com.chinasoft.lgh.movies.datacollector.model.LocateSubImage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LocateSubImageMapper {

    /**
     * save sub image
     * @param locateSubImage sub image
     * @return success
     */
    boolean save(@Param("locateSubImage") LocateSubImage locateSubImage);
}
