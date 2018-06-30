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

    /**
     * query types
     * @return types
     */
    List<String> queryTypes();

    /**
     * query regions
     * @return regions
     */
    List<String> queryRegions();

    /**
     * query count by filter
     * @param movieFilter filter
     * @return count
     */
    int queryCountByFilter(MovieFilter movieFilter);

    /**
     * query by id
     * @param id id
     * @return movie info
     */
    Movie queryById(@Param("id") String id);
}
