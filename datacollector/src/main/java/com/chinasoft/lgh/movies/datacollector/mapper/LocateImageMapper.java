package com.chinasoft.lgh.movies.datacollector.mapper;

import com.chinasoft.lgh.movies.datacollector.model.LocateImage;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

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
