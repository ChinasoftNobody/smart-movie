package com.chinasoft.lgh.movies.datacollector.util;

import com.chinasoft.lgh.movies.datacollector.collect.CollectWorker;
import com.chinasoft.lgh.movies.datacollector.collect.analyze.Analyzer;
import com.chinasoft.lgh.movies.datacollector.collect.client.RestTemplateFactory;
import com.chinasoft.lgh.movies.datacollector.collect.path.PathConfigure;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author Administrator
 */
public class DataCollectorUtil {



    /**
     * 根据URL进行数据爬取
     * @param url url
     */
    public static void collectDataFromUrl(List<String> url, PathConfigure configure, Analyzer analyzer){
        RestTemplate restTemplate = RestTemplateFactory.buildDefault();
        CollectWorker collectWorker = new CollectWorker(restTemplate,configure,url, analyzer);
        ThreadUtil.executorService.submit(collectWorker);
    }
}
