package com.chinasoft.lgh.movies.datacollector.service.impl;

import com.chinasoft.lgh.movies.datacollector.mapper.MovieImageMapper;
import com.chinasoft.lgh.movies.datacollector.model.MovieImage;
import com.chinasoft.lgh.movies.datacollector.service.MovieImageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class MovieImageServiceImpl implements MovieImageService {
    @Resource
    private MovieImageMapper movieImageMapper;

    @Override
    public void batchSave(List<MovieImage> subImage) {
        movieImageMapper.batchSave(subImage);
    }

    @Override
    public List<String> getById(String id) {
        return movieImageMapper.getById(id);
    }

    @Override
    public List<MovieImage> queryNotLocated() {
        return movieImageMapper.queryNotLocated();
    }
}
