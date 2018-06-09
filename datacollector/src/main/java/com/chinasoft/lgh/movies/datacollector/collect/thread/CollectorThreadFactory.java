package com.chinasoft.lgh.movies.datacollector.collect.thread;

import com.chinasoft.lgh.movies.datacollector.collect.CollectWorker;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.concurrent.ThreadFactory;

/**
 * @author Administrator
 */
public class CollectorThreadFactory implements ThreadFactory {

    private static final Log LOG = LogFactory.getLog(CollectorThreadFactory.class);
    private static final String THREAD_GROUP_NAME = "collector";

    @Override
    public Thread newThread(Runnable r) {

        if(r instanceof CollectWorker){
            ThreadGroup threadGroup = new ThreadGroup(THREAD_GROUP_NAME);
            LOG.info("create a new thread for runnable class : " + r.getClass().getName());
            CollectWorker collectWorker = (CollectWorker)r;
            return new Thread(threadGroup,collectWorker,collectWorker.getRootUrl());
        }
        LOG.info("create a new thread for runnable class : " + r.getClass().getName());
        return new Thread(r);
    }
}
