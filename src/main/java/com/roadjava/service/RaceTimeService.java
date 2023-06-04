package com.roadjava.service;

import com.roadjava.entity.RaceTimeDo;
import com.roadjava.req.StudentRequest;
import com.roadjava.res.TableDTO;

public interface RaceTimeService {
    TableDTO retrieveRaceTimes(StudentRequest request);

    boolean add(RaceTimeDo raceDo);

    RaceTimeDo getById(String selectedRace);

    boolean update(RaceTimeDo raceDo);

    boolean delete(String[] selectedRace);
}
