package com.chinasoft.lgh.movies.datacollector.service;

import com.chinasoft.lgh.movies.datacollector.model.UrlHistory;
import com.chinasoft.lgh.movies.datacollector.pojo.UrlHistoryFilter;

import java.util.List;

public interface UrlHistoryService {

    /**
     * batch save
     * @param urlHistories urls
     * @return success
     */
    boolean batchSave(List<UrlHistory> urlHistories);

    /**
     * count by filter
     * @param filter filter
     * @return count
     */
    int countByFilter(UrlHistoryFilter filter);

    /**
     * query by filter
     * @param filter filter
     * @return list
     */
    List<UrlHistory> queryByFilter(UrlHistoryFilter filter);

    /**
     * query threads
     * @return threads
     */
    List<String> queryThreads();
}
