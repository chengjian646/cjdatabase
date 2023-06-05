package com.roadjava.service.interf;

import com.roadjava.entity.SRDo;
import com.roadjava.entity.SelectSRPK;
import com.roadjava.req.StudentRequest;
import com.roadjava.res.TableDTO;

public interface StudentService {
    TableDTO retrieveStudents(StudentRequest request);

    boolean add(SRDo srDo);

    SRDo getById(SelectSRPK selectSRPK);

    boolean update(SRDo srDo);

    boolean delete(SelectSRPK selectSRPK);
}