package com.roadjava.service.interf;

import com.roadjava.entity.RaceTimeDo;
import com.roadjava.req.RaceTimeRequest;
import com.roadjava.res.TableDTO;
import com.roadjava.view.Add.AddRaceTimeView;
import com.roadjava.view.Update.UpdateRaceTimeView;

public interface RaceTimeService {
    TableDTO retrieveRaceTimes(RaceTimeRequest request);

    boolean add(RaceTimeDo raceDo, AddRaceTimeView addRaceTimeView);

    RaceTimeDo getById(String selectedRace);

    boolean update(RaceTimeDo raceDo, UpdateRaceTimeView updateRaceTimeView);

    boolean delete(String[] selectedRace);
}
