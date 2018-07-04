package com.chinasoft.lgh.movies.datacollector.controller.collect;

import com.chinasoft.lgh.movies.datacollector.common.ModuleException;
import com.chinasoft.lgh.movies.datacollector.common.Response;
import com.chinasoft.lgh.movies.datacollector.model.collect.Module;
import com.chinasoft.lgh.movies.datacollector.service.CollectModuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/collect/module",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags = "CollectModule")
public class ModuleController {

    @Resource
    private CollectModuleService collectModuleService;

    @PostMapping("/create")
    @ApiOperation("create a new collect module")
    public Response<Boolean> createModule(@RequestBody Module module){
        boolean flag = false;
        try {
            flag = collectModuleService.create(module);
        } catch (ModuleException e) {
            return new Response<>(e.getMessage());
        }
        return new Response<>(flag);
    }
}
