package com.roadjava.service.impl;

import com.roadjava.entity.SDo;
import com.roadjava.entity.SelectSRPK;
import com.roadjava.req.StudentSRequest;
import com.roadjava.res.TableDTO;
import com.roadjava.service.StudentSService;
import com.roadjava.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class StudentSServiceImpl implements StudentSService {
    @Override
    public TableDTO retrieveStudents(StudentSRequest request) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from (select * from Student ");
        boolean has=false;
        if(request.getSerachKey()!=null&&!"".equals(request.getSerachKey().trim())){
            sql.append("where Sno like '%"+request.getSerachKey().trim()+"%'");
            has=true;
        }
        if(request.getSname()!=null&&!"".equals(request.getSname().trim())){
            if(has==false){
                sql.append(" where ");
                has=true;
            }else{
                sql.append(" and ");
            }
            sql.append("Sname like '%"+request.getSname().trim()+"%'");
        }
        if(request.getSgender()!=null&&!"".equals(request.getSgender().trim())){
            if(has==false){
                sql.append(" where ");
                has=true;
            }else{
                sql.append(" and ");
            }
            sql.append("Sgender like '%"+request.getSgender().trim()+"%'");
        }
        if(request.getSagelow()!=null&&!"".equals(request.getSagelow().trim())&&
                request.getSagehigh()!=null&&!"".equals((request.getSagehigh().trim()))){
            if(has==false){
                sql.append(" where ");
            }else{
                sql.append(" and ");
            }
            sql.append("Sage between ").append(Integer.valueOf(request.getSagelow())).append(" and ")
                    .append(Integer.valueOf(request.getSagehigh()));
        }
        if(request.getSclass()!=null&&!"".equals(request.getSclass().trim())){
            if(has==false){
                sql.append(" where ");
                has=true;
            }else{
                sql.append(" and ");
            }
            sql.append("Class like '%"+request.getSclass().trim()+"%'");
        }
        sql.append(" order by Sno asc )offset ").append(request.getStart()).append(" rows fetch next ")
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
                String sname = rs.getString("Sname");
                String sgender = rs.getString("Sgender");
                int sage = rs.getInt("Sage");
                String sclass = rs.getString("Class");
                oneRecord.addElement(sno);
                oneRecord.addElement(sname);
                oneRecord.addElement(sgender);
                oneRecord.addElement(sage);
                oneRecord.addElement(sclass);
                data.addElement(oneRecord);
            }
            returnDTO.setData(data);

            sql.setLength(0);
            sql.append("select count(*) from Student ");
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
    public boolean add(SDo sDo) {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into Student(Sno,Sname,Sgender,Sage,Class) ");
        sql.append(" values(?,?,?,?,?)");
        Connection conn = null;
        PreparedStatement ps=null;
        try{
            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1,sDo.getSno());
            ps.setString(2,sDo.getSname());
            ps.setString(3,sDo.getSgender());
            ps.setInt(4,sDo.getSage());
            ps.setString(5,sDo.getSclass());
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
    public SDo getById(String selectedStu) {
        StringBuilder sql = new StringBuilder("select * from Student where Sno = ? ");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        SDo sDo = new SDo();
        try{
            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1,selectedStu);
            rs = ps.executeQuery();
            while(rs.next()){
                String sno = rs.getString("Sno");
                String sname = rs.getString("Sname");
                String sgender = rs.getString("Sgender");
                int sage = rs.getInt("Sage");
                String sclass = rs.getString("Class");
                sDo.setSno(sno);
                sDo.setSname(sname);
                sDo.setSgender(sgender);
                sDo.setSage(sage);
                sDo.setSclass(sclass);
            }
            return sDo;
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
    public boolean update(SDo sDo) {
        StringBuilder sql = new StringBuilder();
        sql.append("update Student set Sname=?,Sgender=?,Sage=?,Class=? ");
        sql.append(" where Sno=?");
        Connection conn = null;
        PreparedStatement ps=null;
        try{
            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1,sDo.getSname());
            ps.setString(2,sDo.getSgender());
            ps.setInt(3,sDo.getSage());
            ps.setString(4,sDo.getSclass());
            ps.setString(5,sDo.getSno());
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
    public boolean delete(String[] selectedStu) {
        StringBuilder sql = new StringBuilder();
        int length = selectedStu.length;
        sql.append("delete from Student where Sno in ( ");
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
                ps.setString(i+1,selectedStu[i]);
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
