package com.roadjava.service.interf;

import com.roadjava.entity.SDo;
import com.roadjava.entity.SelectSRPK;
import com.roadjava.req.StudentSRequest;
import com.roadjava.res.TableDTO;
import com.roadjava.view.Add.AddSView;
import com.roadjava.view.Main.MainStudentSView;
import com.roadjava.view.Update.UpdateSView;

public interface StudentSService {
    TableDTO retrieveStudents(StudentSRequest request);

    boolean add(SDo sDo, AddSView addSView);

    SDo getById(String selectedStu);

    boolean update(SDo sDo, UpdateSView updateSView);

    boolean delete(String[] selectedStu, MainStudentSView mainStudentSView);
}
