package com.roadjava.service;

import com.roadjava.entity.RaceDo;
import com.roadjava.entity.RaceDo;
import com.roadjava.req.RaceRequest;
import com.roadjava.res.TableDTO;

public interface RaceService {
    TableDTO retrieveRaces(RaceRequest request);

    boolean add(RaceDo raceDo);

    RaceDo getById(String selectedRace);

    boolean update(RaceDo raceDo);

    boolean delete(String[] selectedRace);
}
