package com.roadjava.entity;

public class SRDo {
    private String Sno;
    private String Rno;
    private Integer Grade;

    private boolean effective;
    public SRDo(){
        effective=true;
    }

    public boolean isEffective() {
        return effective;
    }

    public void setEffective(boolean effective) {
        this.effective = effective;
    }

    public String getSno() {
        return Sno;
    }

    public void setSno(String sno) {
        Sno = sno;
    }

    public String getRno() {
        return Rno;
    }

    public void setRno(String rno) {
        Rno = rno;
    }

    public Integer getGrade() {
        return Grade;
    }

    public void setGrade(Integer grade) {
        Grade = grade;
    }

}
