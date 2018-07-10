package com.chinasoft.lgh.movies.datacollector.service;

import com.chinasoft.lgh.movies.datacollector.model.collect.Plan;

import java.util.List;

public interface PlanService {
    /**
     * query by module id
     * @param id id
     * @return plans
     */
    List<Plan> queryByModuleId(String id);
}
