package com.chinasoft.lgh.movies.datacollector.collect.client;

import org.springframework.web.client.RestTemplate;

/**
 * @author Administrator
 */
public class RestTemplateFactory {

    public static RestTemplate buildDefault(){
        return new RestTemplate();
    }
}
