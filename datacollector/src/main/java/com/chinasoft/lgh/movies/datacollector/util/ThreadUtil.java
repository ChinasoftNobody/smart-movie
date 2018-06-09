package com.chinasoft.lgh.movies.datacollector.util;

import com.chinasoft.lgh.movies.datacollector.collect.thread.CollectorThreadFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 */
public class ThreadUtil {

    public static ExecutorService executorService = new ThreadPoolExecutor(3,5,10,
            TimeUnit.MINUTES,new ArrayBlockingQueue<>(3), new CollectorThreadFactory());
}
