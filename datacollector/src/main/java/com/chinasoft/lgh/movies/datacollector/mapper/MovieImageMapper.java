package com.chinasoft.lgh.movies.datacollector.mapper;

import com.chinasoft.lgh.movies.datacollector.model.MovieImage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieImageMapper {

    /**
     * batch save
     * @param images MovieImages
     */
    void batchSave(@Param("images") List<MovieImage> images);

    /**
     * get sub images by id
     * @param id id
     * @return sub images
     */
    List<String> getById(@Param("id") String id);

    /**
     * query not located
     * @return urls
     */
    List<MovieImage> queryNotLocated();
}
