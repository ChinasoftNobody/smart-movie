package com.chinasoft.lgh.movies.datacollector.collect;

import com.chinasoft.lgh.movies.datacollector.collect.analyze.Analyzer;
import com.chinasoft.lgh.movies.datacollector.collect.path.PathConfigure;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @author Administrator
 */
public class CollectWorker implements Runnable {

    private static final Log LOG = LogFactory.getLog(CollectWorker.class);

    private RestTemplate restTemplate;
    private PathConfigure pathConfigure;
    private String rootUrl;
    private Analyzer analyzer;

    public CollectWorker(RestTemplate restTemplate, PathConfigure pathConfigure, String rootUrl, Analyzer analyzer) {
        this.restTemplate = restTemplate;
        this.pathConfigure = pathConfigure;
        this.rootUrl = rootUrl;
        this.analyzer = analyzer;
    }

    public String getRootUrl() {
        return rootUrl;
    }

    @Override
    public void run() {
        LOG.info("collection work start");
        try {
            collect(rootUrl);
        } catch (Exception e) {
            LOG.error("collection work error for " + e.getMessage(), e);
        }


    }

    /**
     * collect from url
     * @param url url
     */
    private void collect(String url) {
        LOG.info("url :" + url);
        pathConfigure.getExpiredUrl().add(url);
        String result = restTemplate.execute(rootUrl, HttpMethod.GET, null, response -> {
            if (response.getStatusCode().equals(HttpStatus.OK)) {
                LineNumberReader reader = new LineNumberReader(new InputStreamReader(response.getBody(), Charset.forName("gb2312")));
                StringBuilder stringBuilder = new StringBuilder();
                String tempLine = null;
                while ((tempLine = reader.readLine()) != null){
                    stringBuilder.append(tempLine);
                }
                return stringBuilder.toString();
            }
            LOG.info("parse result to document failed");
            return null;
        });
        if (!StringUtils.isEmpty(result)) {
            List<String> urls = analyzer.analyze(result, pathConfigure);
            if(urls != null && !urls.isEmpty()){
                for(String subUrl:urls){
                    pathConfigure.downDeep();
                    collect(subUrl);
                }
            }
            return;
        }
        LOG.info("parse result to document failed");
    }
}
