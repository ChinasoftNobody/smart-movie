package com.chinasoft.lgh.movies.datacollector.collect;

import com.chinasoft.lgh.movies.datacollector.collect.analyze.Analyzer;
import com.chinasoft.lgh.movies.datacollector.collect.path.PathConfigure;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Administrator
 */
public class CollectWorker implements Runnable {

    private static final Log LOG = LogFactory.getLog(CollectWorker.class);

    private RestTemplate restTemplate;
    private PathConfigure pathConfigure;
    private List<String> rootUrls;
    private Analyzer analyzer;

    public CollectWorker(RestTemplate restTemplate, PathConfigure pathConfigure, List<String> urls, Analyzer analyzer) {
        this.restTemplate = restTemplate;
        this.pathConfigure = pathConfigure;
        this.rootUrls = urls;
        this.analyzer = analyzer;
    }

    public List<String> getRootUrls() {
        return rootUrls;
    }

    @Override
    public void run() {
        LOG.info("collection work start");
        Set<String> urlList = new HashSet<>(rootUrls);
        try {
            if (!pathConfigure.isStandardFirst()) {
                for(String url : urlList){
                    deepFirst(url);
                }
            } else {
                urlList.addAll(rootUrls);
                standardFirst(urlList);
            }
        } catch (Exception e) {
            LOG.error("collection work error for " + e.getMessage(), e);
        }


    }

    /**
     * standard first collect
     *
     * @param urlList urlList
     */
    private void standardFirst(Set<String> urlList) {
        if(null == urlList || urlList.isEmpty()){
            LOG.error("urlList is empty");
            return;
        }
        pathConfigure.getExpiredUrl().addAll(urlList);
        pathConfigure.addDeep();
        Set<String> subUrlList = new HashSet<>();
        for (String url : urlList) {
            String documentString = getStringDocumentFromUrl(url);
            if(StringUtils.isEmpty(documentString)){
               LOG.error("get result from url : " + url + " failed");
               continue;
            }
            List<String> subUrls = analyzer.analyze(documentString,pathConfigure);
            if(null != subUrls && !subUrls.isEmpty()){
                subUrlList.addAll(subUrls);
            }
        }
        if(pathConfigure.getCurrentDeep() <= pathConfigure.getDeep() && !subUrlList.isEmpty()){
            standardFirst(subUrlList);
        }

    }

    /**
     * deepFirst from url
     *
     * @param url url
     */
    private void deepFirst(String url) {
        LOG.info("url :" + url);
        pathConfigure.getExpiredUrl().add(url);
        String result = getStringDocumentFromUrl(url);
        if (!StringUtils.isEmpty(result)) {
            List<String> urls = analyzer.analyze(result, pathConfigure);
            if (urls != null && !urls.isEmpty()) {
                for (String subUrl : urls) {
                    pathConfigure.downDeep();
                    deepFirst(subUrl);
                }
            }
            return;
        }
        LOG.info("parse result to document failed");
    }

    /**
     * get string document from remote url
     *
     * @param url url
     * @return string document info
     */
    private String getStringDocumentFromUrl(String url) {
        try {
            return restTemplate.execute(url, HttpMethod.GET, null, response -> {
                if (response.getStatusCode().equals(HttpStatus.OK)) {
                    LineNumberReader reader = new LineNumberReader(new InputStreamReader(response.getBody(), Charset.forName("gb2312")));
                    StringBuilder stringBuilder = new StringBuilder();
                    String tempLine = null;
                    while ((tempLine = reader.readLine()) != null) {
                        stringBuilder.append(tempLine);
                    }
                    return stringBuilder.toString();
                }
                LOG.info("parse result to document failed");
                return null;
            });
        }catch (Exception e){
            LOG.error("get string document from url throw a exception :" + e.getMessage());
        }
        return null;
    }
}
