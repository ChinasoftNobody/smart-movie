package com.chinasoft.lgh.movies.datacollector.mapper;

import com.chinasoft.lgh.movies.datacollector.model.collect.Module;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectModuleMapper {

    /**
     * query by name
     * @param name name
     * @return module
     */
    Module queryByName(@Param("name") String name);

    /**
     * save module
     * @param module module
     * @return result
     */
    boolean save(@Param("module") Module module);
}
