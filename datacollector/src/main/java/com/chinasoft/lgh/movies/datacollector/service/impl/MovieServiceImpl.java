package com.chinasoft.lgh.movies.datacollector.service.impl;

import com.chinasoft.lgh.movies.datacollector.mapper.MovieMapper;
import com.chinasoft.lgh.movies.datacollector.model.Movie;
import com.chinasoft.lgh.movies.datacollector.service.MovieService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MovieServiceImpl implements MovieService {

    private static final Log LOG = LogFactory.getLog(MovieServiceImpl.class);

    @Resource
    private MovieMapper movieMapper;

    @Override
    public void batchSave(List<Movie> movies) {
        if(movieMapper.saveBatch(movies)){
            LOG.info("save movies success size " + movies.size());
        }
    }
}