package com.chinasoft.lgh.movies.datacollector.collect.path;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Administrator
 */
public class PathConfigure {

    private List<String> cssQuery;

    private int deep;

    private boolean standardFirst;

    private int currentDeep;

    private String baseUrl;

    private SubUrlMatcher subUrlMatcher;

    private Set<String> expiredUrl = new HashSet<>();

    public PathConfigure(SubUrlMatcher subUrlMatcher) {
        this.subUrlMatcher = subUrlMatcher;
    }

    public void addDeep(){
        currentDeep += 1;
    }

    public void downDeep(){
        currentDeep -= 1;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public List<String> getCssQuery() {
        return cssQuery;
    }

    public void setCssQuery(List<String> cssQuery) {
        this.cssQuery = cssQuery;
    }

    public int getDeep() {
        return deep;
    }

    public void setDeep(int deep) {
        this.deep = deep;
    }

    public boolean isStandardFirst() {
        return standardFirst;
    }

    public void setStandardFirst(boolean standardFirst) {
        this.standardFirst = standardFirst;
    }

    public int getCurrentDeep() {
        return currentDeep;
    }

    public SubUrlMatcher getSubUrlMatcher() {
        return subUrlMatcher;
    }

    public void setSubUrlMatcher(SubUrlMatcher subUrlMatcher) {
        this.subUrlMatcher = subUrlMatcher;
    }

    public Set<String> getExpiredUrl() {
        return expiredUrl;
    }

    public void setExpiredUrl(Set<String> expiredUrl) {
        this.expiredUrl = expiredUrl;
    }
}
