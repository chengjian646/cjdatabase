package com.roadjava.service.impl;

import com.roadjava.req.StudentRequest;
import com.roadjava.res.TableDTO;
import com.roadjava.service.StudentService;
import com.roadjava.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class StudentServiceImpl implements StudentService {
    @Override
    public TableDTO retrieveStudents(StudentRequest request) {
        StringBuilder sql = new StringBuilder();
        /*sql.append("select * from StuRace");
        if(request.getSerachKey()!=null&&!"".equals(request.getSerachKey().trim())){
            sql.append(" where Sno like '%"+request.getSerachKey().trim()+"%'");
        }*/
        sql.append("select * from (select * from StuRace ");
        if(request.getSerachKey()!=null&&!"".equals(request.getSerachKey().trim())){
            sql.append("where Sno like '%"+request.getSerachKey().trim()+"%'");
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
}
