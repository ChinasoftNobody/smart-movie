package com.chinasoft.lgh.movies.datacollector.collect.path;

import java.util.List;

/**
 * @author Administrator
 */
public class PathConfigure {

    private List<String> cssQuery;

    private int deep;

    private boolean standardFirst;

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
}
