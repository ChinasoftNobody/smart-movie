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
            String result = restTemplate.execute(rootUrl, HttpMethod.GET, null, new ResponseExtractor<String>() {
                @Override
                public String extractData(ClientHttpResponse response) throws IOException {
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
                }
            });
            if (!StringUtils.isEmpty(result)) {
                analyzer.analyze(result, pathConfigure);
                return;
            }
            LOG.info("parse result to document failed");
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }


    }
}
