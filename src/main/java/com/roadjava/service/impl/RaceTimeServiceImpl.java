package com.roadjava.service.impl;

import com.roadjava.entity.RaceTimeDo;
import com.roadjava.req.RaceTimeRequest;
import com.roadjava.res.TableDTO;
import com.roadjava.service.interf.RaceTimeService;
import com.roadjava.util.DBUtil;
import com.roadjava.view.Add.AddRaceTimeView;
import com.roadjava.view.Update.UpdateRaceTimeView;

import javax.swing.*;
import java.sql.*;
import java.util.Vector;

public class RaceTimeServiceImpl implements RaceTimeService {
    @Override
    public TableDTO retrieveRaceTimes(RaceTimeRequest request) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from (select * from RaceTime ");
        boolean hasPK=false;
        if(request.getSerachKey()!=null&&!"".equals(request.getSerachKey().trim())){
            sql.append("where Rno like '%"+request.getSerachKey().trim()+"%'");
            hasPK=true;
        }
        if(request.getJno()!=null&&!"".equals(request.getJno().trim())){
            if(hasPK==false){
                sql.append(" where ");
            }else{
                sql.append(" and ");
            }
            sql.append("Jno like '%"+request.getJno().trim()+"%'");
        }
        sql.append(" order by RDate asc )offset ").append(request.getStart()).append(" rows fetch next ")
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
                Timestamp rdate = rs.getTimestamp("Rdate");
                String rplace = rs.getString("Rplace");
                String jno = rs.getString("Jno");
                oneRecord.addElement(rno);
                oneRecord.addElement(rdate);
                oneRecord.addElement(rplace);
                oneRecord.addElement(jno);
                data.addElement(oneRecord);
            }
            returnDTO.setData(data);

            sql.setLength(0);
            sql.append("select count(*) from RaceTime ");
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
    public boolean add(RaceTimeDo raceTimeDo, AddRaceTimeView addRaceTimeView) {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into RaceTime(Rno,Rdate,Rplace,Jno) ");
        sql.append(" values(?,?,?,?)");
        Connection conn = null;
        PreparedStatement ps=null;
        try{
            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1,raceTimeDo.getRno());
            ps.setTimestamp(2,raceTimeDo.getRtimestamp());
            ps.setString(3,raceTimeDo.getRplace());
            ps.setString(4,raceTimeDo.getJno());
            return ps.executeUpdate()==1;
        }catch (SQLException e){
            if(e.getMessage().contains("SYS_C007548")){
                JOptionPane.showMessageDialog(addRaceTimeView,"该比赛赛程已被添加！");
            }else if(e.getMessage().contains("SYS_C007549")){
                JOptionPane.showMessageDialog(addRaceTimeView,"该裁判不存在！");
            }else if(e.getMessage().contains("SYS_C007550")) {
                JOptionPane.showMessageDialog(addRaceTimeView, "该比赛不存在！");
            }else{
                JOptionPane.showMessageDialog(addRaceTimeView,e.getMessage());
            }
            //e.printStackTrace();
        }finally {
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
        return false;
    }

    @Override
    public RaceTimeDo getById(String selectedRaceTime) {
        StringBuilder sql = new StringBuilder("select * from RaceTime where Rno = ?");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        RaceTimeDo raceTimeDo = new RaceTimeDo();
        try{
            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1,selectedRaceTime);
            rs = ps.executeQuery();
            while(rs.next()){
                String rno = rs.getString("Rno");
                Timestamp timestamp = rs.getTimestamp("RDate");
                String rplace = rs.getString("RPlace");
                String jno = rs.getString("Jno");
                raceTimeDo.setRno(rno);
                raceTimeDo.setRtimestamp(timestamp);
                raceTimeDo.setRplace(rplace);
                raceTimeDo.setJno(jno);
            }
            return raceTimeDo;
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
    public boolean update(RaceTimeDo raceTimeDo, UpdateRaceTimeView updateRaceTimeView) {
        StringBuilder sql = new StringBuilder();
        sql.append("update RaceTime set RDate=? , RPlace=? ,Jno=? ");
        sql.append(" where Rno=?");
        Connection conn = null;
        PreparedStatement ps=null;
        try{
            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql.toString());
            ps.setTimestamp(1,raceTimeDo.getRtimestamp());
            ps.setString(2,raceTimeDo.getRplace());
            ps.setString(3,raceTimeDo.getJno());
            ps.setString(4,raceTimeDo.getRno());
            return ps.executeUpdate()==1;
        }catch (SQLException e){
            if(e.getMessage().contains("SYS_C007548")){
                JOptionPane.showMessageDialog(updateRaceTimeView,"该比赛赛程已被添加！");
            }else if(e.getMessage().contains("SYS_C007549")){
                JOptionPane.showMessageDialog(updateRaceTimeView,"该裁判不存在！");
            }else if(e.getMessage().contains("SYS_C007550")){
                JOptionPane.showMessageDialog(updateRaceTimeView,"该比赛不存在！");
            }else{
                JOptionPane.showMessageDialog(updateRaceTimeView,e.getMessage());
            }
            //e.printStackTrace();
        }finally {
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
        return false;
    }

    @Override
    public boolean delete(String[] selectedRaceTime) {
        StringBuilder sql = new StringBuilder();
        int length = selectedRaceTime.length;
        sql.append("delete from RaceTime where Rno in ( ");
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
                ps.setString(i+1,selectedRaceTime[i]);
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
