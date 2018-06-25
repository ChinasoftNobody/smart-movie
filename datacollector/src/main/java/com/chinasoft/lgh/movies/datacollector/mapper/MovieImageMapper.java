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
}
