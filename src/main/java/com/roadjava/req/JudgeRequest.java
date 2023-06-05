package com.roadjava.req;

public class JudgeRequest {
    private int pageNow;
    private int pageSize;
    private int start;
    private String serachKey;//查询词(主键)
    private String jname;

    public String getJname() {
        return jname;
    }

    public void setJname(String jname) {
        this.jname = jname;
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
