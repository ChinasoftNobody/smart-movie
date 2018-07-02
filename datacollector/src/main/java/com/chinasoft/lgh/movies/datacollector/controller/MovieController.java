package com.chinasoft.lgh.movies.datacollector.controller;

import com.chinasoft.lgh.movies.datacollector.common.Response;
import com.chinasoft.lgh.movies.datacollector.model.LocateImage;
import com.chinasoft.lgh.movies.datacollector.model.Movie;
import com.chinasoft.lgh.movies.datacollector.pojo.MovieFilter;
import com.chinasoft.lgh.movies.datacollector.pojo.Pages;
import com.chinasoft.lgh.movies.datacollector.service.LocateImageService;
import com.chinasoft.lgh.movies.datacollector.service.MovieService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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

    @Resource
    private LocateImageService locateImageService;

    @PostMapping(value = "/query", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response<Pages<Movie>> queryMovies(@RequestBody MovieFilter movieFilter) {
        Pages<Movie> movies = movieService.queryByFilter(movieFilter);
        if (null == movies) {
            return new Response<>(new Pages<>());
        }
        return new Response<>(movies);
    }

    @PostMapping(value = "/types", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response<List<String>> types() {
        List<String> types = movieService.queryTypes();
        if (types == null) {
            return new Response<>(new ArrayList<>());
        }
        return new Response<>(types);
    }

    @PostMapping(value = "/regions", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response<List<String>> regions() {
        List<String> types = movieService.queryRegions();
        if (types == null) {
            return new Response<>(new ArrayList<>());
        }
        return new Response<>(types);
    }

    @PostMapping(value = "/detail", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response<Movie> detail(@RequestBody Movie movie) {
        if (movie == null || StringUtils.isEmpty(movie.getId())) {
            return new Response<>("id is empty");
        }
        Movie movie1 = movieService.queryById(movie.getId());
        if (movie1 == null) {
            return new Response<>("movie not exist");
        }
        LocateImage locateImage = locateImageService.queryById(movie.getId());
        if (locateImage != null && locateImage.getImage() != null) {
            movie1.setMainImage("http://localhost:8081/movie/movie/image/" + movie.getId());
        }
        return new Response<>(movie1);
    }

    @GetMapping(value = "/stream")
    public void movieStream(HttpServletResponse response) {
        String path = "/home/lgh/code/platform/src/assets/video/test.mp4";
        try (FileInputStream fileInputStream = new FileInputStream(path)) {
            byte[] bytes = new byte[1024];
            while (fileInputStream.read(bytes) > 0) {
                response.getOutputStream().write(bytes);
                response.getOutputStream().flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    @PostMapping("/image/{id}/check")
    @ApiOperation(value = "locate image check")
    public Response<Boolean> checkLocateImage(@PathVariable(value = "id") String id) {
        if (StringUtils.isEmpty(id)) {
            return new Response<>(false);
        }
        LocateImage locateImage = locateImageService.queryById(id);
        if (locateImage == null || locateImage.getImage() == null) {
            return new Response<>(false);
        }
        return new Response<>(true);
    }

    @GetMapping("/image/{id}")
    public void locateImage(@PathVariable(value = "id") String id, HttpServletResponse response) {
        if (StringUtils.isEmpty(id)) {
            return;
        }
        LocateImage locateImage = locateImageService.queryById(id);
        if (locateImage == null || locateImage.getImage() == null) {
            return;
        }
        locateImageService.writeImage(response,locateImage);
    }
}
