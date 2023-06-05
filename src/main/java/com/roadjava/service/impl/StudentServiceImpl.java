package com.roadjava.service.impl;

import com.roadjava.entity.SRDo;
import com.roadjava.entity.SelectSRPK;
import com.roadjava.req.StudentRequest;
import com.roadjava.res.TableDTO;
import com.roadjava.service.interf.StudentService;
import com.roadjava.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class StudentServiceImpl implements StudentService {
    @Override
    public TableDTO retrieveStudents(StudentRequest request) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from (select * from StuRace ");
        boolean hasK=false;
        if(request.getSerachKey()!=null&&!"".equals(request.getSerachKey().trim())){
            sql.append("where Sno like '%"+request.getSerachKey().trim()+"%'");
            hasK=true;
        }
        if(request.getGradeLow()!=null&&!"".equals(request.getGradeLow().trim())&&
           request.getGradeHigh()!=null&&!"".equals(request.getGradeHigh().trim())){
            if(hasK==false){
                sql.append(" where ");
            }else{
                sql.append(" and ");
            }
            sql.append("Grade between ").append(Integer.valueOf(request.getGradeLow())).append(" and ")
                    .append(Integer.valueOf(request.getGradeHigh()));
        }
        sql.append(" order by Rno asc,Grade desc )offset ").append(request.getStart()).append(" rows fetch next ")
                .append(request.getPageSize()).append(" rows only");//控制每页的数量

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        TableDTO returnDTO = new TableDTO();

        try{
            conn = DBUtil.getConn();
            ps=conn.prepareStatement(sql.toString());
            rs=ps.executeQuery();
            Vector<Vector<Object>> data = new Vector<>();
            while(rs.next()){
                Vector<Object> oneRecord = new Vector<>();
                String sno = rs.getString("Sno");
                String rno = rs.getString("Rno");
                int grade = rs.getInt("Grade");
                oneRecord.addElement(sno);
                oneRecord.addElement(rno);
                oneRecord.addElement(grade);
                data.addElement(oneRecord);
            }
            returnDTO.setData(data);

            sql.setLength(0);
            sql.append("select count(*) from StuRace ");
            if(request.getSerachKey()!=null&&!"".equals(request.getSerachKey().trim())){
                sql.append(" where Sno like '%"+request.getSerachKey().trim()+"%'");
            }
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            while(rs.next()){
                int count = rs.getInt(1);
                returnDTO.setTotalCount(count);
            }
            return returnDTO;
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.closeRs(rs);
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
        return null;
    }

    @Override
    public boolean add(SRDo srDo) {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into StuRace(Sno,Rno,Grade) ");
        sql.append(" values(?,?,?)");
        Connection conn = null;
        PreparedStatement ps=null;
        try{
            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1,srDo.getSno());
            ps.setString(2,srDo.getRno());
            ps.setInt(3,srDo.getGrade());
            return ps.executeUpdate()==1;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
        return false;
    }

    @Override
    public SRDo getById(SelectSRPK selectSRPK) {
        StringBuilder sql = new StringBuilder("select * from StuRace where Sno = ? and Rno = ?");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        SRDo srDo = new SRDo();
        try{
            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1,selectSRPK.getSno()[0]);
            ps.setString(2,selectSRPK.getRno()[0]);
            rs = ps.executeQuery();
            while(rs.next()){
                String sno = rs.getString("Sno");
                String rno = rs.getString("Rno");
                int grade = rs.getInt("Grade");
                srDo.setSno(sno);
                srDo.setRno(rno);
                srDo.setGrade(grade);
            }
            return srDo;
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.closeRs(rs);
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
        return null;
    }

    @Override
    public boolean update(SRDo srDo) {
        StringBuilder sql = new StringBuilder();
        sql.append("update StuRace set Grade=? ");
        sql.append(" where Sno=? and Rno=?");
        Connection conn = null;
        PreparedStatement ps=null;
        try{
            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql.toString());
            ps.setInt(1,srDo.getGrade());
            ps.setString(2,srDo.getSno());
            ps.setString(3,srDo.getRno());
            return ps.executeUpdate()==1;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
        return false;
    }

    @Override
    public boolean delete(SelectSRPK selectSRPK) {
        StringBuilder sql = new StringBuilder();
        int length = selectSRPK.getSno().length;
        sql.append("delete from StuRace where Sno in ( ");
        for (int i = 0; i < length; i++) {
            if(i == (length-1)){
                sql.append(" ? ");
            }else {
                sql.append(" ?, ");
            }
        }
        sql.append(" ) and Rno in ( ");
        for (int i = 0; i < length; i++) {
            if(i == (length-1)){
                sql.append(" ? ");
            }else {
                sql.append(" ?, ");
            }
        }
        sql.append(" ) ");
        Connection conn = null;
        PreparedStatement ps=null;
        try{
            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql.toString());
            //设置参数
            int i;
            for (i = 0; i < length; i++) {
                ps.setString(i+1,selectSRPK.getSno()[i]);
            }
            for(;i<2*length;i++){
                ps.setString(i+1,selectSRPK.getRno()[i-length]);
            }
            return ps.executeUpdate()==length;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
        return false;
    }
}
