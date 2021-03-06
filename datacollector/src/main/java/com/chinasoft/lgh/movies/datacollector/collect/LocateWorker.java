package com.chinasoft.lgh.movies.datacollector.collect;

import com.chinasoft.lgh.movies.datacollector.collect.client.RestTemplateFactory;
import com.chinasoft.lgh.movies.datacollector.model.LocateImage;
import com.chinasoft.lgh.movies.datacollector.model.Movie;
import com.chinasoft.lgh.movies.datacollector.service.LocateImageService;
import com.chinasoft.lgh.movies.datacollector.service.MovieService;
import com.chinasoft.lgh.movies.datacollector.util.ImageLocateUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class LocateWorker implements Runnable {

    private static final Log LOG = LogFactory.getLog(LocateWorker.class);

    private MovieService movieService;

    private LocateImageService locateImageService;

    public LocateWorker(MovieService movieService, LocateImageService locateImageService) {
        this.locateImageService = locateImageService;
        this.movieService = movieService;
    }

    @Override
    public void run() {
        List<Movie> movies = movieService.queryNotLocatedImages();
        if (movies != null && !movies.isEmpty()) {
            for (Movie movie : movies) {
                String url = movie.getMainImage();
                if (StringUtils.isEmpty(url)) {
                    continue;
                }
                locateImage(movie);
            }
        }
    }


    /**
     * locate main image
     *
     * @param movie movie
     */
    private void locateImage(Movie movie) {
        byte[] bytes = ImageLocateUtil.getImageFromUrl(movie.getMainImage());
        if(bytes != null){
            LocateImage locateImage = new LocateImage();
            locateImage.setId(movie.getId());
            locateImage.setImage(bytes);
            if(!locateImageService.saveLocateImage(locateImage)){
                LOG.error("save locate image error");
            }
        }
    }
}
