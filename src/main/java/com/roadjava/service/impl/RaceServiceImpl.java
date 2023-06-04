package com.roadjava.service.impl;

import com.roadjava.entity.RaceDo;
import com.roadjava.req.StudentRequest;
import com.roadjava.res.TableDTO;
import com.roadjava.service.RaceService;
import com.roadjava.service.StudentService;
import com.roadjava.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class RaceServiceImpl implements RaceService {
    @Override
    public TableDTO retrieveRaces(StudentRequest request) {
        StringBuilder sql = new StringBuilder();
        /*sql.append("select * from StuRace");
        if(request.getSearchKey()!=null&&!"".equals(request.getSerachKey().trim())){
            sql.append(" where Rpno like '%"+request.getSerachKey().trim()+"%'");
        }*/
        sql.append("select * from (select * from Race ");
        if(request.getSerachKey()!=null&&!"".equals(request.getSerachKey().trim())){
            sql.append("where Rno like '%"+request.getSerachKey().trim()+"%'");
        }
        sql.append(" order by Rno asc )offset ").append(request.getStart()).append(" rows fetch next ")
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
                String rno = rs.getString("Rno");
                String rname = rs.getString("Rname");
                String rpno = rs.getString("Rpno");
                oneRecord.addElement(rno);
                oneRecord.addElement(rname);
                oneRecord.addElement(rpno);
                data.addElement(oneRecord);
            }
            returnDTO.setData(data);

            sql.setLength(0);
            sql.append("select count(*) from Race ");
            if(request.getSerachKey()!=null&&!"".equals(request.getSerachKey().trim())){
                sql.append(" where Rno like '%"+request.getSerachKey().trim()+"%'");
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
    public boolean add(RaceDo raceDo) {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into Race(Rno,Rname,Rpno) ");
        sql.append(" values(?,?,?)");
        Connection conn = null;
        PreparedStatement ps=null;
        try{
            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1,raceDo.getRno());
            ps.setString(2,raceDo.getRname());
            ps.setString(3,raceDo.getRpno());
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
    public RaceDo getById(String selectedRace) {
        StringBuilder sql = new StringBuilder("select * from Race where Rno = ?");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        RaceDo raceDo = new RaceDo();
        try{
            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1,selectedRace);
            rs = ps.executeQuery();
            while(rs.next()){
                String rno = rs.getString("Rno");
                String rname = rs.getString("Rname");
                String rpno = rs.getString("Rpno");
                raceDo.setRno(rno);
                raceDo.setRname(rname);
                raceDo.setRpno(rpno);
            }
            return raceDo;
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
    public boolean update(RaceDo raceDo) {
        StringBuilder sql = new StringBuilder();
        sql.append("update Race set Rname=? , Rpno=? ");
        sql.append(" where Rno=?");
        Connection conn = null;
        PreparedStatement ps=null;
        try{
            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1,raceDo.getRname());
            ps.setString(2,raceDo.getRpno());
            ps.setString(3,raceDo.getRno());
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
    public boolean delete(String[] selectedRace) {
        StringBuilder sql = new StringBuilder();
        int length = selectedRace.length;
        sql.append("delete from Race where Rno in ( ");
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
            for (int i = 0; i < length; i++) {
                ps.setString(i+1,selectedRace[i]);
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
