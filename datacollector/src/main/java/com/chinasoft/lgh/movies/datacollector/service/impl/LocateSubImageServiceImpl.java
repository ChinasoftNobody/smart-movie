package com.chinasoft.lgh.movies.datacollector.service.impl;

import com.chinasoft.lgh.movies.datacollector.mapper.LocateSubImageMapper;
import com.chinasoft.lgh.movies.datacollector.model.LocateSubImage;
import com.chinasoft.lgh.movies.datacollector.service.LocateSubImageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
@Transactional(rollbackOn = Exception.class)
public class LocateSubImageServiceImpl implements LocateSubImageService {

    @Resource
    private LocateSubImageMapper locateSubImageMapper;

    @Override
    public boolean save(LocateSubImage locateSubImage) {
        return locateSubImageMapper.save(locateSubImage);
    }
}
