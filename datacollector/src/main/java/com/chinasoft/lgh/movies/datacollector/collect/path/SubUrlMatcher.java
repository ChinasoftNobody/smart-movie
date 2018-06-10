package com.chinasoft.lgh.movies.datacollector.collect.path;

public interface SubUrlMatcher {

    /**
     * match url
     * @param subUrl subUrl
     * @return match
     */
    boolean match(String subUrl);
}
