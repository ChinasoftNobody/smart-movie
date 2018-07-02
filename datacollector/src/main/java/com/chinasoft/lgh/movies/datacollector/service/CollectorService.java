package com.chinasoft.lgh.movies.datacollector.service;

import com.chinasoft.lgh.movies.datacollector.common.CollectionException;
import com.chinasoft.lgh.movies.datacollector.common.Response;

import java.util.List;

/**
 * @author Administrator
 */
public interface CollectorService {

    /**
     * 采集地址信息
     * 循环采集
     * @param url 地址
     * @return 结果
     * @throws  CollectionException exception
     */
    Response<String> collect(List<String> url) throws CollectionException;

    /**
     * locateImages
     */
    void locateImages();
}
