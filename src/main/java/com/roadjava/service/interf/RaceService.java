package com.roadjava.service.interf;

import com.roadjava.entity.RaceDo;
import com.roadjava.entity.RaceDo;
import com.roadjava.req.RaceRequest;
import com.roadjava.res.TableDTO;
import com.roadjava.view.Add.AddRaceView;
import com.roadjava.view.Main.MainRaceView;
import com.roadjava.view.Update.UpdateRaceView;

public interface RaceService {
    TableDTO retrieveRaces(RaceRequest request);

    boolean add(RaceDo raceDo, AddRaceView addRaceView);

    RaceDo getById(String selectedRace);

    boolean update(RaceDo raceDo, UpdateRaceView updateRaceView);

    boolean delete(String[] selectedRace, MainRaceView mainRaceView);
}
