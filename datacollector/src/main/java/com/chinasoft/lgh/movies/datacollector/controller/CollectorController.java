package com.chinasoft.lgh.movies.datacollector.controller;

import com.chinasoft.lgh.movies.datacollector.common.Response;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("/collector")
public class CollectorController {


    @PostMapping(value = "/url",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Response<String> test(){
        //TODO 解析电影网站信息
        return new Response<>(Response.SUCCESS,true);
    }
}
