package com.chinasoft.lgh.movies.datacollector.service.impl;

import com.chinasoft.lgh.movies.datacollector.common.ModuleException;
import com.chinasoft.lgh.movies.datacollector.mapper.CollectModuleMapper;
import com.chinasoft.lgh.movies.datacollector.model.collect.Module;
import com.chinasoft.lgh.movies.datacollector.service.CollectModuleService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.UUID;

@Service
public class CollectModuleServiceImpl implements CollectModuleService {

    @Resource
    private CollectModuleMapper collectModuleMapper;

    @Override
    public boolean create(Module module) throws ModuleException {
        if(module == null||StringUtils.isEmpty(module.getName())){
            throw new ModuleException("name is empty");
        }
        Module temp = collectModuleMapper.queryByName(module.getName());
        if(temp != null){
            throw new ModuleException("module name exists");
        }
        module.setId(UUID.randomUUID().toString());
        return collectModuleMapper.save(module);
    }
}
