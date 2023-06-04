package com.roadjava.service;

import com.roadjava.entity.RaceDo;
import com.roadjava.entity.RaceDo;
import com.roadjava.req.StudentRequest;
import com.roadjava.res.TableDTO;

public interface RaceService {
    TableDTO retrieveRaces(StudentRequest request);

    boolean add(RaceDo raceDo);

    RaceDo getById(String selectedRace);

    boolean update(RaceDo raceDo);

    boolean delete(String[] selectedRace);
}
