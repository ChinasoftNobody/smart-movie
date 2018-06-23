package com.chinasoft.lgh.movies.datacollector.controller;

import com.chinasoft.lgh.movies.datacollector.common.Response;
import com.chinasoft.lgh.movies.datacollector.model.Movie;
import com.chinasoft.lgh.movies.datacollector.pojo.MovieFilter;
import com.chinasoft.lgh.movies.datacollector.service.MovieService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("/movie")
public class MovieController {

    @Resource
    private MovieService movieService;

    @PostMapping(value = "/query",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response<List<Movie>> queryMovies(@RequestBody MovieFilter movieFilter){
        List<Movie> movies = movieService.queryByFilter(movieFilter);
        if(null == movies || movies.isEmpty()){
            return new Response<>(new ArrayList<>());
        }
        return new Response<>(movies);
    }

    @PostMapping(value = "/types",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response<List<String>> types(){
        List<String> types = movieService.queryTypes();
        if (types == null) {
            return new Response<>(new ArrayList<>());
        }
        return new Response<>(types);
    }

    @PostMapping(value = "/regions",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response<List<String>> regions(){
        List<String> types = movieService.queryRegions();
        if (types == null) {
            return new Response<>(new ArrayList<>());
        }
        return new Response<>(types);
    }

}
