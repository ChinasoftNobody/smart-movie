package com.chinasoft.lgh.movies.datacollector.util;

import com.chinasoft.lgh.movies.datacollector.collect.client.RestTemplateFactory;
import com.chinasoft.lgh.movies.datacollector.model.LocateImage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ImageLocateUtil {

    private static final Log LOG = LogFactory.getLog(ImageLocateUtil.class);

    public static byte[] getImageFromUrl(String url){
        RestTemplate restTemplate = RestTemplateFactory.buildDefault();
        try {
            ResponseEntity<byte[]> response = restTemplate.exchange(url, HttpMethod.GET, null, byte[].class, "");
            if (response.getStatusCode().equals(HttpStatus.OK)) {
                return response.getBody();
            }else {
                LOG.error("locate image error: " + response.getStatusCode());
            }
        } catch (Exception e) {
            LOG.error("locate image error: ", e);
        }
        return null;
    }
}
