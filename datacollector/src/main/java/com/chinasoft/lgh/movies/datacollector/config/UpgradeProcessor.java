package com.chinasoft.lgh.movies.datacollector.config;

import com.chinasoft.lgh.movies.datacollector.mapper.UpgradeMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * 数据库迭代管理
 *
 * @author Administrator
 */
@Component
public class UpgradeProcessor implements ApplicationListener<ContextRefreshedEvent> {

    @Resource
    private Environment environment;

    @Resource
    private UpgradeMapper upgradeMapper;

    private static final Log LOG = LogFactory.getLog(UpgradeProcessor.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        String path = environment.getProperty("database.upgrade.path");
        if (StringUtils.isEmpty(path)) {
            LOG.error("database upgrade path not found");
            SpringApplication.exit(event.getApplicationContext());
        }
        upgradeMapper.init();

        try {
            org.springframework.core.io.Resource[] resources = event.getApplicationContext().getResources(path);
            if (null != resources && resources.length > 0) {
                for (org.springframework.core.io.Resource resource : resources) {

                }
            }
        } catch (IOException e) {
            LOG.error("get database upgrade files failed");
            SpringApplication.exit(event.getApplicationContext());
        }

    }

}
