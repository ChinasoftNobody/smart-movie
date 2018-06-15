package com.chinasoft.lgh.movies.datacollector.mapper;

import com.chinasoft.lgh.movies.datacollector.model.Movie;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieMapper {

    /**
     * batch save
     * @param movies movies
     * @return success
     */
    boolean saveBatch(@Param("movies") List<Movie> movies);
}
