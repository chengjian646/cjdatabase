package com.roadjava.service.impl;

import com.roadjava.entity.JDo;
import com.roadjava.req.StudentRequest;
import com.roadjava.res.TableDTO;
import com.roadjava.service.JudgeService;
import com.roadjava.service.StudentSService;
import com.roadjava.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class JudgeServiceImpl implements JudgeService {
    @Override
    public TableDTO retrieveStudents(StudentRequest request) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from (select * from Judge ");
        if(request.getSerachKey()!=null&&!"".equals(request.getSerachKey().trim())){
            sql.append("where Jno like '%"+request.getSerachKey().trim()+"%'");
        }
        sql.append(" order by Jno asc )offset ").append(request.getStart()).append(" rows fetch next ")
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
                String jno = rs.getString("Jno");
                String jname = rs.getString("Jname");
                oneRecord.addElement(jno);
                oneRecord.addElement(jname);
                data.addElement(oneRecord);
            }
            returnDTO.setData(data);

            sql.setLength(0);
            sql.append("select count(*) from Judge ");
            if(request.getSerachKey()!=null&&!"".equals(request.getSerachKey().trim())){
                sql.append(" where Jno like '%"+request.getSerachKey().trim()+"%'");
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
    public boolean add(JDo jDo) {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into Judge(Jno,Jname) ");
        sql.append(" values(?,?)");
        Connection conn = null;
        PreparedStatement ps=null;
        try{
            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1,jDo.getJno());
            ps.setString(2,jDo.getJname());
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
    public JDo getById(String selectedJudge) {
        StringBuilder sql = new StringBuilder("select * from Judge where Jno = ? ");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        JDo jDo = new JDo();
        try{
            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1,selectedJudge);
            rs = ps.executeQuery();
            while(rs.next()){
                String jno = rs.getString("Jno");
                String jname = rs.getString("Jname");
                jDo.setJno(jno);
                jDo.setJname(jname);
            }
            return jDo;
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
    public boolean update(JDo jDo) {
        StringBuilder sql = new StringBuilder();
        sql.append("update Judge set Jname=? ");
        sql.append(" where Jno=?");
        Connection conn = null;
        PreparedStatement ps=null;
        try{
            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1,jDo.getJname());
            ps.setString(2,jDo.getJno());
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
    public boolean delete(String[] selectedJudge) {
        StringBuilder sql = new StringBuilder();
        int length = selectedJudge.length;
        sql.append("delete from Judge where Jno in ( ");
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
                ps.setString(i+1,selectedJudge[i]);
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
