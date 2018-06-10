package com.chinasoft.lgh.movies.datacollector.service.impl;

import com.chinasoft.lgh.movies.datacollector.collect.analyze.HtmlAnalyzer;
import com.chinasoft.lgh.movies.datacollector.collect.path.PathConfigure;
import com.chinasoft.lgh.movies.datacollector.collect.path.SubUrlMatcher;
import com.chinasoft.lgh.movies.datacollector.common.CollectionException;
import com.chinasoft.lgh.movies.datacollector.common.Response;
import com.chinasoft.lgh.movies.datacollector.service.CollectorService;
import com.chinasoft.lgh.movies.datacollector.util.DataCollectorUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Administrator
 */
@Service
public class CollectorServiceImpl implements CollectorService {

    private static final Pattern BASE_URL_PATTERN = Pattern.compile("(?<baseUrl> (http|https))");

    @Override
    public Response<String> collect(String url) throws CollectionException{
        if(StringUtils.isEmpty(url)){
            throw new CollectionException("url is empty");
        }
        PathConfigure configure = new PathConfigure(new SubUrlMatcher() {
            @Override
            public boolean match(String subUrl) {
                return subUrl.startsWith("/") && !subUrl.endsWith("/");
            }
        });
        List<String> stringList = new ArrayList<>();
        stringList.add(".co_content8>ul>table>tbody>tr");
        configure.setDeep(5);
        configure.setStandardFirst(true);
        configure.setCssQuery(stringList);
        configure.setBaseUrl("http://www.ygdy8.com");
        DataCollectorUtil.collectDataFromUrl(url,configure,new HtmlAnalyzer());
        return new Response<>("success",true);
    }

    /**
     * resolve baseUrl
     * @param url url
     * @return baseUrl
     */
    private String resolveBaseUrl(String url) throws CollectionException{
        Matcher matcher = BASE_URL_PATTERN.matcher(url);
        if(!matcher.matches()){
            throw new CollectionException("invalid url :"+ url);
        }
        matcher.group("baseUrl");
        return null;
    }
}
