package com.chinasoft.lgh.movies.datacollector.mapper;

import com.chinasoft.lgh.movies.datacollector.model.collect.Plan;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanMapper {

    /**
     * query by moduleId id
     * @param moduleId moduleId
     * @return plans
     */
    List<Plan> queryByModuleId(@Param("moduleId") String moduleId);
}
