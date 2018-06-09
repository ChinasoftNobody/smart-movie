package com.chinasoft.lgh.movies.datacollector.service.impl;

import com.chinasoft.lgh.movies.datacollector.collect.analyze.HtmlAnalyzer;
import com.chinasoft.lgh.movies.datacollector.collect.path.PathConfigure;
import com.chinasoft.lgh.movies.datacollector.common.CollectionException;
import com.chinasoft.lgh.movies.datacollector.common.Response;
import com.chinasoft.lgh.movies.datacollector.service.CollectorService;
import com.chinasoft.lgh.movies.datacollector.util.DataCollectorUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
@Service
public class CollectorServiceImpl implements CollectorService {

    @Override
    public Response<String> collect(String url) throws CollectionException{
        if(StringUtils.isEmpty(url)){
            throw new CollectionException("url is empty");
        }
        PathConfigure configure = new PathConfigure();
        List<String> stringList = new ArrayList<>();
        stringList.add("table>tbody>tr");
        configure.setDeep(1);
        configure.setStandardFirst(true);
        configure.setCssQuery(stringList);
        DataCollectorUtil.collectDataFromUrl(url,configure,new HtmlAnalyzer());
        return new Response<>("success",true);
    }
}
