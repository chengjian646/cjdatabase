package com.roadjava.entity;

public class SelectSRPK {
    private String[] Sno;
    private String[] Rno;

    public SelectSRPK(int length) {
        Sno = new String[length];
        Rno = new String[length];
    }

    public String[] getSno() {
        return Sno;
    }

    public void setSno(String[] sno) {
        Sno = sno;
    }

    public String[] getRno() {
        return Rno;
    }

    public void setRno(String[] rno) {
        Rno = rno;
    }
}
