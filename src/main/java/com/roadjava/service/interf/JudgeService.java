package com.roadjava.service.interf;

import com.roadjava.entity.JDo;
import com.roadjava.req.JudgeRequest;
import com.roadjava.res.TableDTO;
import com.roadjava.view.Add.AddJudgeView;
import com.roadjava.view.Main.MainJudgeView;

public interface JudgeService {
    TableDTO retrieveStudents(JudgeRequest request);

    boolean add(JDo jDo, AddJudgeView addJudgeView);

    JDo getById(String selectedJudge);

    boolean update(JDo jDo);

    boolean delete(String[] selectedJudge, MainJudgeView mainJudgeView);
}
