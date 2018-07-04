package com.chinasoft.lgh.movies.datacollector.controller;

import com.chinasoft.lgh.movies.datacollector.common.Response;
import com.chinasoft.lgh.movies.datacollector.model.UrlHistory;
import com.chinasoft.lgh.movies.datacollector.pojo.PageInfo;
import com.chinasoft.lgh.movies.datacollector.pojo.Pages;
import com.chinasoft.lgh.movies.datacollector.pojo.UrlHistoryFilter;
import com.chinasoft.lgh.movies.datacollector.service.UrlHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/collect")
@Api(tags = "collect")
public class CollectController {

    @Resource
    private UrlHistoryService urlHistoryService;

    @PostMapping(value = "/urlHistory",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation("url history")
    public Response<Pages<UrlHistory>> history(@RequestBody UrlHistoryFilter filter){
        Pages<UrlHistory> pages = new Pages<>();
        pages.setTotal(urlHistoryService.countByFilter(filter));
        if(pages.getTotal()>0){
            pages.setList(urlHistoryService.queryByFilter(filter));
        }
        return new Response<>(pages);
    }

    @PostMapping(value = "/threads",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation("threads")
    public Response<List<String>> threads(){
        List<String> threads = urlHistoryService.queryThreads();
        if(threads == null){
            threads = new ArrayList<>(0);
        }
        return new Response<>(threads);
    }


}
