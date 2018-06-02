package com.chinasoft.lgh.movies.datacollector.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Administrator
 */
@Repository
public interface UpgradeMapper {

    int upgrade(@Param("sql") String sql);

    /**
     * 初始化数据库
     * @return 初始化数据库
     */
    boolean init();
}
