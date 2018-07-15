package com.chinasoft.lgh.movies.datacollector.controller.collect;

import com.chinasoft.lgh.movies.datacollector.common.Response;
import com.chinasoft.lgh.movies.datacollector.model.collect.Module;
import com.chinasoft.lgh.movies.datacollector.model.collect.Plan;
import com.chinasoft.lgh.movies.datacollector.service.CollectModuleService;
import com.chinasoft.lgh.movies.datacollector.service.PlanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/collect/module/plan", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags = "module plan")
public class PlanController {

    @Resource
    private PlanService planService;

    @Resource
    private CollectModuleService collectModuleService;

    @PostMapping("/queryByModuleId")
    @ApiOperation("query plans by module id")
    public Response<List<Plan>> queryByModuleId(@RequestBody Module module) {
        if (module == null || StringUtils.isEmpty(module.getId())) {
            return new Response<>(new ArrayList<>());
        }
        List<Plan> plans = planService.queryByModuleId(module.getId());
        if (plans == null) {
            plans = new ArrayList<>();
        }
        return new Response<>(plans);
    }

    @PostMapping("/createPlan")
    @ApiOperation("create a plan")
    public Response<Boolean> createPlan(@RequestBody Plan plan) {
        if (plan == null || StringUtils.isEmpty(plan.getModuleId())) {
            return new Response<>("module id invalid");
        }
        Module module = collectModuleService.queryById(plan.getModuleId());
        if(module == null){
            return new Response<>("module not exist");
        }
        plan.setId(UUID.randomUUID().toString());
        if(!planService.createPlan(plan)){
            return new Response<>("create failed");
        }
        return new Response<>(Boolean.TRUE);
    }
}
