package com.chinasoft.lgh.movies.datacollector.service.impl;

import com.chinasoft.lgh.movies.datacollector.mapper.PlanMapper;
import com.chinasoft.lgh.movies.datacollector.model.collect.Plan;
import com.chinasoft.lgh.movies.datacollector.service.PlanService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
public class PlanServiceImpl implements PlanService {

    @Resource
    private PlanMapper planMapper;

    @Override
    public List<Plan> queryByModuleId(String moduleId) {
        if(StringUtils.isEmpty(moduleId)){
            return null;
        }
        return planMapper.queryByModuleId(moduleId);
    }

    @Override
    public boolean createPlan(Plan plan) {
        if(StringUtils.isEmpty(plan.getId())){
            plan.setId(UUID.randomUUID().toString());
        }
        return planMapper.save(plan);
    }
}
