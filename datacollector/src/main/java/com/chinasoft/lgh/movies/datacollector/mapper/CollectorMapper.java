package com.chinasoft.lgh.movies.datacollector.mapper;

import com.chinasoft.lgh.movies.datacollector.model.Test;
import org.springframework.stereotype.Repository;

/**
 * @author Administrator
 */
@Repository
public interface CollectorMapper {

    Test test();
}
