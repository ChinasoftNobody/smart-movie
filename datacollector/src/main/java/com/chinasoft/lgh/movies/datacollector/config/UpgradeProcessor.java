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
import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.regex.Pattern;

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

    private static Pattern upgradePattern = Pattern.compile("upgrade_[0-9]+\\.sql");

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
                    if (!resource.exists() || !resource.isReadable()) {
                        LOG.error("database upgrade is un readable");
                    }
                    processUpgrade(resource.getFile());
                }
            }
        } catch (IOException e) {
            LOG.error("get database upgrade files failed");
            SpringApplication.exit(event.getApplicationContext());
        }
    }

    private void processUpgrade(File file) {
        HashMap<String, Integer> map = upgradeMapper.getLatest();
        int latest = (map == null ? 0 : map.get("version"));
        String fileName = file.getName();
        if (upgradePattern.matcher(fileName).matches()) {
            int version = Integer.valueOf(fileName.substring(fileName.indexOf('_') + 1, fileName.indexOf('.')));
            if (version > latest) {
                FileInputStream stream = null;
                try {
                    stream = new FileInputStream(file);
                    byte[] bytes = new byte[stream.available()];
                    stream.read(bytes);
                    String sql = new String(bytes);
                    executeUpgrade(fileName,sql,version);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (stream != null) {
                        try {
                            stream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } else {
            LOG.error(fileName + " not match upgrade regex");
        }
    }

    private void executeUpgrade(String fileName,String sql,int version) {
        try {
            upgradeMapper.executeSql(sql);
            upgradeMapper.upgradeVersion(fileName,sql,version);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
