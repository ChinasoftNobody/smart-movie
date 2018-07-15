package com.chinasoft.lgh.movies.datacollector.service;

import com.chinasoft.lgh.movies.datacollector.common.ModuleException;
import com.chinasoft.lgh.movies.datacollector.model.collect.Module;

import java.util.List;

public interface CollectModuleService {

    /**
     * create a new collect module
     * @param module module
     * @return result
     */
    boolean create(Module module)throws ModuleException;

    /**
     * query all modules
     * @return modules
     */
    List<Module> queryAll();

    /**
     * delete module
     * @param module module
     * @return flag
     */
    Boolean delete(Module module);

    /**
     * query by id
     * @param moduleId moduleId
     * @return module info
     */
    Module queryById(String moduleId);
}
