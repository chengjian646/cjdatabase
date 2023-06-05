package com.roadjava.handler.Add;

import com.roadjava.entity.RaceTimeDo;
import com.roadjava.service.RaceTimeService;
import com.roadjava.service.impl.RaceTimeServiceImpl;
import com.roadjava.student.view.Add.AddRaceTimeView;
import com.roadjava.student.view.Main.MainRaceTimeView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

public class AddRaceTimeViewHandler extends KeyAdapter implements ActionListener {
    private AddRaceTimeView addRaceTimeView;
    private MainRaceTimeView mainRaceTimeView;
    public AddRaceTimeViewHandler(AddRaceTimeView addRaceTimeView, MainRaceTimeView mainRaceTimeView){
        this.addRaceTimeView=addRaceTimeView;
        this.mainRaceTimeView=mainRaceTimeView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("添加".equals(text)){
            RaceTimeService raceTimeService =  new RaceTimeServiceImpl();
            RaceTimeDo raceTimeDo = addRaceTimeView.bulidRaceTimeDo();
            boolean addResult = raceTimeService.add(raceTimeDo);
            if(addResult){
                mainRaceTimeView.reloadTable();
                addRaceTimeView.dispose();//销毁窗口
            }else{
                JOptionPane.showMessageDialog(addRaceTimeView,"添加失败！");
            }
        }
    }
}
