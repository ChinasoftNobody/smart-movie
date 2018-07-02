package com.chinasoft.lgh.movies.datacollector.controller;

import com.chinasoft.lgh.movies.datacollector.common.Response;
import com.chinasoft.lgh.movies.datacollector.service.CollectorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/test")
@Api(tags = "test")
public class TestCOntroller {

    @Resource
    private CollectorService collectorService;

    @PostMapping(value = "/locate",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "locate image")
    public Response<Boolean> locateImages(){
        collectorService.locateImages();
        return new Response<>(true);
    }
}
