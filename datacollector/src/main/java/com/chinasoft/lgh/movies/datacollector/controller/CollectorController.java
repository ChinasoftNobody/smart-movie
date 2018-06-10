package com.chinasoft.lgh.movies.datacollector.controller;

import com.chinasoft.lgh.movies.datacollector.common.CollectionException;
import com.chinasoft.lgh.movies.datacollector.common.Response;
import com.chinasoft.lgh.movies.datacollector.service.CollectorService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("/collector")
public class CollectorController {

    @Resource
    private CollectorService collectorService;


    @PostMapping(value = "/url",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Response<String> url() throws CollectionException{
        String rootUrl = "http://www.dytt8.net/";
        return collectorService.collect(rootUrl);
    }
}