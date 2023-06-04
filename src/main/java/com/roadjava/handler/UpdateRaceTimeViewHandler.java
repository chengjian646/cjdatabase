package com.roadjava.handler;

import com.roadjava.entity.RaceTimeDo;
import com.roadjava.service.RaceTimeService;
import com.roadjava.service.impl.RaceTimeServiceImpl;
import com.roadjava.student.view.MainRaceTimeView;
import com.roadjava.student.view.UpdateRaceTimeView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

public class UpdateRaceTimeViewHandler extends KeyAdapter implements ActionListener {
    private UpdateRaceTimeView updateRaceTimeView;
    private MainRaceTimeView mainRaceTimeView;
    public UpdateRaceTimeViewHandler(UpdateRaceTimeView updateRaceTimeView, MainRaceTimeView mainRaceTimeView){
        this.updateRaceTimeView=updateRaceTimeView;
        this.mainRaceTimeView=mainRaceTimeView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("修改".equals(text)){
            RaceTimeService raceTimeService =  new RaceTimeServiceImpl();
            RaceTimeDo raceTimeDo = updateRaceTimeView.bulidUpdatedRaceTimeDo();

            boolean updateResult = raceTimeService.update(raceTimeDo);
            if(updateResult){
                mainRaceTimeView.reloadTable();
                updateRaceTimeView.dispose();//销毁窗口
            }else{
                JOptionPane.showMessageDialog(updateRaceTimeView,"修改失败！");
            }
        }
    }


}
