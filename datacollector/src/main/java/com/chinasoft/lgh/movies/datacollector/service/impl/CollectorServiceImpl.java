package com.chinasoft.lgh.movies.datacollector.service.impl;

import com.chinasoft.lgh.movies.datacollector.collect.LocateSubWorker;
import com.chinasoft.lgh.movies.datacollector.collect.LocateWorker;
import com.chinasoft.lgh.movies.datacollector.collect.analyze.HtmlAnalyzer;
import com.chinasoft.lgh.movies.datacollector.collect.path.PathConfigure;
import com.chinasoft.lgh.movies.datacollector.collect.path.SubUrlMatcher;
import com.chinasoft.lgh.movies.datacollector.common.CollectionException;
import com.chinasoft.lgh.movies.datacollector.common.Response;
import com.chinasoft.lgh.movies.datacollector.service.*;
import com.chinasoft.lgh.movies.datacollector.util.DataCollectorUtil;
import com.chinasoft.lgh.movies.datacollector.util.ThreadUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
@Service
public class CollectorServiceImpl implements CollectorService {

    @Resource
    private MovieService movieService;

    @Resource
    private MovieImageService movieImageService;

    @Resource
    private LocateImageService locateImageService;

    @Resource
    private LocateSubImageServiceImpl locateSubImageService;

    @Resource
    private UrlHistoryService urlHistoryService;


    @Override
    public Response<String> collect(List<String> urls) throws CollectionException {
        if (StringUtils.isEmpty(urls)) {
            throw new CollectionException("urls is empty");
        }
        PathConfigure configure = new PathConfigure(new SubUrlMatcher() {
            @Override
            public boolean match(String subUrl) {
                return subUrl.startsWith("/") && !subUrl.endsWith("/");
            }
        });
        List<String> stringList = new ArrayList<>();
        stringList.add("body>div#header>div.contain>div.bd2>div.bd3>div.bd3r>div.co_area2>div.co_content8>ul>div>div#Zoom>span");
        configure.setDeep(1000000);
        configure.setStandardFirst(true);
        configure.setCssQuery(stringList);
        configure.setBaseUrl("http://www.ygdy8.net");
        DataCollectorUtil.collectDataFromUrl(urls, configure,
                new HtmlAnalyzer(movieService, movieImageService,urlHistoryService));
        return new Response<>("success", true);
    }

    @Override
    public void locateImages() {
        LocateWorker locateWorker = new LocateWorker(movieService, locateImageService);
        ThreadUtil.executorService.submit(locateWorker);
    }

    @Override
    public void locateSubImages() {
        LocateSubWorker locateSubWorker = new LocateSubWorker(movieImageService, locateSubImageService);
        ThreadUtil.executorService.submit(locateSubWorker);
    }

}
