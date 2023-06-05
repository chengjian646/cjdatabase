package com.roadjava.req;

public class StudentSRequest {
    private int pageNow;
    private int pageSize;
    private int start;
    private String serachKey;//查询词(主键)

    private String sname;
    private String sgender;
    private String sagelow;
    private String sagehigh;
    private String sclass;

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSgender() {
        return sgender;
    }

    public void setSgender(String sgender) {
        this.sgender = sgender;
    }

    public String getSagelow() {
        return sagelow;
    }

    public void setSagelow(String sagelow) {
        this.sagelow = sagelow;
    }

    public String getSagehigh() {
        return sagehigh;
    }

    public void setSagehigh(String sagehigh) {
        this.sagehigh = sagehigh;
    }

    public String getSclass() {
        return sclass;
    }

    public void setSclass(String sclass) {
        this.sclass = sclass;
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
