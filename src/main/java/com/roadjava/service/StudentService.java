package com.roadjava.service;

import com.roadjava.entity.SRDo;
import com.roadjava.req.StudentRequest;
import com.roadjava.res.TableDTO;

public interface StudentService {
    TableDTO retrieveStudents(StudentRequest request);

    boolean add(SRDo srDo);

    SRDo getById(String selectedRecordsId);

    boolean update(SRDo srDo);
}
