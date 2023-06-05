package com.roadjava.req;

public class RaceRequest {
    private int pageNow;
    private int pageSize;
    private int start;
    private String serachKey;//查询词(主键)
    private String Rname;

    public String getRname() {
        return Rname;
    }

    public void setRname(String rname) {
        Rname = rname;
    }

    public int getStart() {
        return (pageNow-1)*pageSize;
    }

    public int getPageNow() {
        return pageNow;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSerachKey() {
        return serachKey;
    }

    public void setSerachKey(String serachKey) {
        this.serachKey = serachKey;
    }
}
