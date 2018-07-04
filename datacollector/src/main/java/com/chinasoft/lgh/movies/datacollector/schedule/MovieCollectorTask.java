package com.chinasoft.lgh.movies.datacollector.schedule;

import com.chinasoft.lgh.movies.datacollector.common.CollectionException;
import com.chinasoft.lgh.movies.datacollector.service.CollectorService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Component
public class MovieCollectorTask {

    private static final Log LOG = LogFactory.getLog(MovieCollectorTask.class);

    @Resource
    private CollectorService collectorService;

    @Scheduled(cron = "0 0 1 * * ?")
    public void collectYGDY() {
        LOG.info("movie collector task start...");
        String rootUrl = "http://www.ygdy8.com";
        String url = "http://www.ygdy8.net";
        List<String> urls = new ArrayList<>();
        urls.add(rootUrl);
        urls.add(url);
        try {
            collectorService.collect(urls);
        } catch (CollectionException e) {
            e.printStackTrace();
            LOG.error("movie collector task occur a exception", e);
        }
        LOG.info("movie collector task end");
    }

    /**
     * save images to local database
     */
    @Scheduled(cron = "0 0 4 * * ?")
    public void locateImages(){
        LOG.info("locate images task start...");
        collectorService.locateImages();
        LOG.info("locate images task end");
    }
    @Scheduled(cron = "0 0 10 * * ?")
    public void locateSubImages(){
        collectorService.locateSubImages();
    }
}
