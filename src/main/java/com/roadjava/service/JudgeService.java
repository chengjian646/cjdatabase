package com.roadjava.service;

import com.roadjava.entity.JDo;
import com.roadjava.req.StudentRequest;
import com.roadjava.res.TableDTO;

public interface JudgeService {
    TableDTO retrieveStudents(StudentRequest request);

    boolean add(JDo jDo);

    JDo getById(String selectedJudge);

    boolean update(JDo jDo);

    boolean delete(String[] selectedJudge);
}
