package com.roadjava.handler;

import com.roadjava.entity.RaceDo;
import com.roadjava.service.RaceService;
import com.roadjava.service.impl.RaceServiceImpl;
import com.roadjava.student.view.MainRaceView;
import com.roadjava.student.view.UpdateRaceView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

public class UpdateRaceViewHandler extends KeyAdapter implements ActionListener {
    private UpdateRaceView updateRaceView;
    private MainRaceView mainRaceView;
    public UpdateRaceViewHandler(UpdateRaceView updateRaceView, MainRaceView mainRaceView){
        this.updateRaceView=updateRaceView;
        this.mainRaceView=mainRaceView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("修改".equals(text)){
            RaceService raceService =  new RaceServiceImpl();
            RaceDo raceDo = updateRaceView.bulidUpdatedRaceDo();
            boolean updateResult = raceService.update(raceDo);
            if(updateResult){
                mainRaceView.reloadTable();
                updateRaceView.dispose();//销毁窗口
            }else{
                JOptionPane.showMessageDialog(updateRaceView,"修改失败！");
            }
        }
    }


}
