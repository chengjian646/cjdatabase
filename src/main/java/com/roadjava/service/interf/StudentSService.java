package com.roadjava.service.interf;

import com.roadjava.entity.SDo;
import com.roadjava.entity.SelectSRPK;
import com.roadjava.req.StudentSRequest;
import com.roadjava.res.TableDTO;

public interface StudentSService {
    TableDTO retrieveStudents(StudentSRequest request);

    boolean add(SDo sDo);

    SDo getById(String selectedStu);

    boolean update(SDo sDo);

    boolean delete(String[] selectedStu);
}
