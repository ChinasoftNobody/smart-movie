package com.chinasoft.lgh.movies.datacollector.mapper;

import com.chinasoft.lgh.movies.datacollector.model.UrlHistory;
import com.chinasoft.lgh.movies.datacollector.pojo.UrlHistoryFilter;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UrlHistoryMapper {

    /**
     * batch save
     * @param urlHistories urls
     * @return success
     */
    boolean batchSave(@Param("urlHistories") List<UrlHistory> urlHistories);

    /**
     * query by filter
     * @param filter filter
     * @return list
     */
    List<UrlHistory> queryByFilter(@Param("filter") UrlHistoryFilter filter);


    /**
     * count by filter
     * @param filter filter
     * @return count
     */
    int countByFilter(@Param("filter") UrlHistoryFilter filter);

    /**
     * query Threads
     * @return threads
     */
    List<String> queryThreads();
}
