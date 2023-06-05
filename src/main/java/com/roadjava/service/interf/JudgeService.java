package com.roadjava.service.interf;

import com.roadjava.entity.JDo;
import com.roadjava.req.JudgeRequest;
import com.roadjava.res.TableDTO;

public interface JudgeService {
    TableDTO retrieveStudents(JudgeRequest request);

    boolean add(JDo jDo);

    JDo getById(String selectedJudge);

    boolean update(JDo jDo);

    boolean delete(String[] selectedJudge);
}
