package com.roadjava.entity;


import java.sql.Timestamp;
public class RaceTimeDo {
    private String Rno;
    private Timestamp Rtimestamp;
    private String Rplace;
    private String Jno;


    public Timestamp getRtimestamp() {
        return Rtimestamp;
    }

    public void setRtimestamp(Timestamp rtimestamp) {
        Rtimestamp = rtimestamp;
    }

    public String getRno() {
        return Rno;
    }

    public void setRno(String rno) {
        Rno = rno;
    }


    public String getRplace() {
        return Rplace;
    }

    public void setRplace(String rplace) {
        Rplace = rplace;
    }

    public String getJno() {
        return Jno;
    }

    public void setJno(String jno) {
        Jno = jno;
    }
}
