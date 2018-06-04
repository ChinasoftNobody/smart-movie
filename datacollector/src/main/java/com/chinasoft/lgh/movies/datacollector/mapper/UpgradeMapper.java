package com.chinasoft.lgh.movies.datacollector.mapper;

import org.springframework.stereotype.Repository;

import java.util.HashMap;

/**
 * @author Administrator
 */
@Repository
public interface UpgradeMapper {

    /**
     * 初始化数据库
     * @return 初始化数据库
     */
    boolean init();

    /**
     * 返回最新版本
     * @return 返回最新版本
     */
    HashMap<String,Integer> getLatest();
}
