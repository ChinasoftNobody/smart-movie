package com.chinasoft.lgh.movies.datacollector.collect;

import com.chinasoft.lgh.movies.datacollector.model.LocateSubImage;
import com.chinasoft.lgh.movies.datacollector.model.MovieImage;
import com.chinasoft.lgh.movies.datacollector.service.LocateSubImageService;
import com.chinasoft.lgh.movies.datacollector.service.MovieImageService;
import com.chinasoft.lgh.movies.datacollector.util.ImageLocateUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

public class LocateSubWorker implements Runnable {
    private static final Log LOG = LogFactory.getLog(LocateSubWorker.class);

    private MovieImageService movieImageService;
    private LocateSubImageService locateSubImageService;

    public LocateSubWorker(MovieImageService movieImageService, LocateSubImageService locateSubImageService) {
        this.movieImageService = movieImageService;
        this.locateSubImageService = locateSubImageService;
    }

    @Override
    public void run() {
        LOG.info("LocateSubWorker work start...");
        List<MovieImage> movieImages = movieImageService.queryNotLocated();
        if (movieImages != null && !movieImages.isEmpty()) {
            for (MovieImage movieImage : movieImages) {
                byte[] image = ImageLocateUtil.getImageFromUrl(movieImage.getImage());
                if (image == null) {
                    continue;
                }
                LocateSubImage locateSubImage = new LocateSubImage();
                locateSubImage.setId(movieImage.getId());
                locateSubImage.setMovieId(movieImage.getMovieId());
                locateSubImage.setImage(image);
                if(!locateSubImageService.save(locateSubImage)){
                    LOG.error("save locate sub image failed");
                }
            }
        }
        LOG.info("LocateSubWorker work end");
    }
}
