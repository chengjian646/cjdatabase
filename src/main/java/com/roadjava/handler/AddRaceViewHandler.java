package com.roadjava.handler;

import com.roadjava.entity.RaceDo;
import com.roadjava.entity.SRDo;
import com.roadjava.service.RaceService;
import com.roadjava.service.impl.RaceServiceImpl;
import com.roadjava.service.impl.StudentServiceImpl;
import com.roadjava.student.view.AddRaceView;
import com.roadjava.student.view.AddRaceView;
import com.roadjava.student.view.MainRaceView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

public class AddRaceViewHandler extends KeyAdapter implements ActionListener {
    private AddRaceView addRaceView;
    private MainRaceView mainRaceView;
    public AddRaceViewHandler(AddRaceView addRaceView, MainRaceView mainRaceView){
        this.addRaceView=addRaceView;
        this.mainRaceView=mainRaceView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("添加".equals(text)){
            RaceService raceService =  new RaceServiceImpl();
            RaceDo raceDo = addRaceView.bulidRaceDo();
            boolean addResult = raceService.add(raceDo);
            if(addResult){
                mainRaceView.reloadTable();
                addRaceView.dispose();//销毁窗口
            }else{
                JOptionPane.showMessageDialog(addRaceView,"添加失败！");
            }
        }
    }
}
