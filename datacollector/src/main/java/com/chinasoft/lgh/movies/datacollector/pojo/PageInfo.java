package com.chinasoft.lgh.movies.datacollector.pojo;

/**
 * @author Administrator
 */
public class PageInfo {
    private int start;
    private int pageIndex;
    private int pageSize;

    public int getStart() {
        return pageIndex * pageSize;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }
}
