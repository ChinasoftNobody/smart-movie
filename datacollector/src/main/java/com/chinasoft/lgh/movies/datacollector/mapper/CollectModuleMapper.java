package com.chinasoft.lgh.movies.datacollector.mapper;

import com.chinasoft.lgh.movies.datacollector.model.collect.Module;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    /**
     * query all modules
     * @return modules
     */
    List<Module> queryAll();

    /**
     * delete by id
     * @param id id
     * @return result
     */
    Boolean deleteById(@Param("id") String id);

    /**
     * query by id
     * @param id id
     * @return module
     */
    Module queryById(@Param("id") String id);
}
