package com.chinasoft.lgh.movies.datacollector.service;

import com.chinasoft.lgh.movies.datacollector.common.ModuleException;
import com.chinasoft.lgh.movies.datacollector.model.collect.Module;

public interface CollectModuleService {

    /**
     * create a new collect module
     * @param module module
     * @return result
     */
    boolean create(Module module)throws ModuleException;
}
