package com.chinasoft.lgh.movies.datacollector.service.impl;

import com.chinasoft.lgh.movies.datacollector.collect.analyze.HtmlAnalyzer;
import com.chinasoft.lgh.movies.datacollector.collect.path.PathConfigure;
import com.chinasoft.lgh.movies.datacollector.collect.path.SubUrlMatcher;
import com.chinasoft.lgh.movies.datacollector.common.CollectionException;
import com.chinasoft.lgh.movies.datacollector.common.Response;
import com.chinasoft.lgh.movies.datacollector.service.CollectorService;
import com.chinasoft.lgh.movies.datacollector.service.MovieImageService;
import com.chinasoft.lgh.movies.datacollector.service.MovieService;
import com.chinasoft.lgh.movies.datacollector.util.DataCollectorUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Administrator
 */
@Service
public class CollectorServiceImpl implements CollectorService {

    private static final Pattern BASE_URL_PATTERN = Pattern.compile("(?<baseUrl> (http|https))");

    @Resource
    private MovieService movieService;

    @Resource
    private MovieImageService movieImageService;


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
        configure.setBaseUrl("http://www.ygdy8.com");
        DataCollectorUtil.collectDataFromUrl(urls, configure, new HtmlAnalyzer(movieService, movieImageService));
        return new Response<>("success", true);
    }
}
