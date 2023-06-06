package com.roadjava.handler.Update;

import com.roadjava.entity.RaceTimeDo;
import com.roadjava.service.interf.RaceTimeService;
import com.roadjava.service.impl.RaceTimeServiceImpl;
import com.roadjava.view.Main.MainRaceTimeView;
import com.roadjava.view.Update.UpdateRaceTimeView;

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
            if(!raceTimeDo.isEffective()){
                return;
            }
            boolean updateResult = raceTimeService.update(raceTimeDo,updateRaceTimeView);
            if(updateResult){
                mainRaceTimeView.reloadTable();
                updateRaceTimeView.dispose();//销毁窗口
            }else{
                JOptionPane.showMessageDialog(updateRaceTimeView,"修改失败！");
            }
        }
    }


}
