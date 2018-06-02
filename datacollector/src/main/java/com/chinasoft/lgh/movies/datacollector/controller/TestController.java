package com.chinasoft.lgh.movies.datacollector.controller;

import com.chinasoft.lgh.movies.datacollector.common.Response;
import com.chinasoft.lgh.movies.datacollector.mapper.TestMapper;
import com.chinasoft.lgh.movies.datacollector.model.Test;
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
@RequestMapping("/test")
public class TestController {
    @Resource
    private TestMapper testMapper;

    @PostMapping(value = "/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Response<Test> test(){
        Test test = testMapper.test();
        return new Response<>(test);
    }
}
