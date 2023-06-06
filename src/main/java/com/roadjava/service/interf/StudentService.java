package com.roadjava.service.interf;

import com.roadjava.entity.SRDo;
import com.roadjava.entity.SelectSRPK;
import com.roadjava.req.StudentRequest;
import com.roadjava.res.TableDTO;
import com.roadjava.view.Add.AddSRView;
import com.roadjava.view.Update.UpdateSRView;

public interface StudentService {
    TableDTO retrieveStudents(StudentRequest request);

    boolean add(SRDo srDo, AddSRView addSRView);

    SRDo getById(SelectSRPK selectSRPK);

    boolean update(SRDo srDo, UpdateSRView updateSRView);

    boolean delete(SelectSRPK selectSRPK);
}
