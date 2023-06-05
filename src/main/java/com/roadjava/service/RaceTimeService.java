package com.roadjava.service;

import com.roadjava.entity.RaceTimeDo;
import com.roadjava.req.RaceTimeRequest;
import com.roadjava.res.TableDTO;

public interface RaceTimeService {
    TableDTO retrieveRaceTimes(RaceTimeRequest request);

    boolean add(RaceTimeDo raceDo);

    RaceTimeDo getById(String selectedRace);

    boolean update(RaceTimeDo raceDo);

    boolean delete(String[] selectedRace);
}
