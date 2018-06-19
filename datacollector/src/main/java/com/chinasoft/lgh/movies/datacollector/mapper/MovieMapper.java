package com.chinasoft.lgh.movies.datacollector.mapper;

import com.chinasoft.lgh.movies.datacollector.model.Movie;
import com.chinasoft.lgh.movies.datacollector.pojo.MovieFilter;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Administrator
 */
@Repository
public interface MovieMapper {

    /**
     * batch save
     * @param movies movies
     * @return success
     */
    boolean saveBatch(@Param("movies") List<Movie> movies);

    /**
     * query movie by filter
     * @param movieFilter movieFilter
     * @return movies
     */
    List<Movie> queryByFilter(MovieFilter movieFilter);
}
