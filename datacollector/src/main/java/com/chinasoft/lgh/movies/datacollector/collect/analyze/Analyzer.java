package com.chinasoft.lgh.movies.datacollector.collect.analyze;

import com.chinasoft.lgh.movies.datacollector.collect.path.PathConfigure;

/**
 * 分析器接口
 *
 * @author Administrator
 */
public interface Analyzer {

    /**
     * 解析结果信息
     * @param document        结果信息
     * @param pathConfigure configure*/
    void analyze(String document, PathConfigure pathConfigure);
}
