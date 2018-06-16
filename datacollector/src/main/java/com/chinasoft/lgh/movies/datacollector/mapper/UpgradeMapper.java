package com.chinasoft.lgh.movies.datacollector.mapper;

import org.apache.ibatis.annotations.Param;
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

    /**
     * execute sql
     * @param sql sql
     */
    void executeSql(@Param("sql") String sql);

    /**
     * upgrade version
     * @param fileName filename
     * @param sql sql
     * @param version version
     */
    void upgradeVersion(@Param("fileName") String fileName, @Param("sql") String sql, @Param("version") int version);
}
