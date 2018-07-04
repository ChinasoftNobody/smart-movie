package com.chinasoft.lgh.movies.datacollector.service.impl;

import com.chinasoft.lgh.movies.datacollector.mapper.UrlHistoryMapper;
import com.chinasoft.lgh.movies.datacollector.model.UrlHistory;
import com.chinasoft.lgh.movies.datacollector.pojo.UrlHistoryFilter;
import com.chinasoft.lgh.movies.datacollector.service.UrlHistoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UrlHistoryServiceImpl implements UrlHistoryService {

    @Resource
    private UrlHistoryMapper urlHistoryMapper;

    @Override
    public boolean batchSave(List<UrlHistory> urlHistories) {
        return urlHistoryMapper.batchSave(urlHistories);
    }

    @Override
    public int countByFilter(UrlHistoryFilter filter) {
        return urlHistoryMapper.countByFilter(filter);
    }

    @Override
    public List<UrlHistory> queryByFilter(UrlHistoryFilter filter) {
        return urlHistoryMapper.queryByFilter(filter);
    }

    @Override
    public List<String> queryThreads() {
        return urlHistoryMapper.queryThreads();
    }
}
