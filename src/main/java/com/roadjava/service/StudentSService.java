package com.roadjava.service;

import com.roadjava.entity.SDo;
import com.roadjava.entity.SelectSRPK;
import com.roadjava.req.StudentRequest;
import com.roadjava.res.TableDTO;

public interface StudentSService {
    TableDTO retrieveStudents(StudentRequest request);

    boolean add(SDo sDo);

    SDo getById(String selectedStu);

    boolean update(SDo sDo);

    boolean delete(String[] selectedStu);
}
