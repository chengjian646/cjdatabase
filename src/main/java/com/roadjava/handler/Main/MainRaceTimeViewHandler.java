package com.roadjava.handler.Main;

import com.roadjava.service.RaceTimeService;
import com.roadjava.service.impl.RaceTimeServiceImpl;
import com.roadjava.student.view.Add.AddRaceTimeView;
import com.roadjava.student.view.Main.MainRaceTimeView;
import com.roadjava.student.view.Update.UpdateRaceTimeView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

public class MainRaceTimeViewHandler extends KeyAdapter implements ActionListener {
    private MainRaceTimeView mainRaceTimeView;
    public MainRaceTimeViewHandler(MainRaceTimeView mainRaceTimeView){
        this.mainRaceTimeView=mainRaceTimeView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("增加".equals(text)){
            new AddRaceTimeView(mainRaceTimeView);
        }else if("修改".equals(text)){
            String[] selectedRaceTimes = mainRaceTimeView.getSelectedRaceTimes();
            if(selectedRaceTimes.length==0){
                JOptionPane.showMessageDialog(mainRaceTimeView,"请选择一行!");
                return;
            }
            if(selectedRaceTimes.length!=1){
                JOptionPane.showMessageDialog(mainRaceTimeView,"一次只能修改一行!");
                return;
            }
            new UpdateRaceTimeView(mainRaceTimeView,selectedRaceTimes[0]);
        }else if("删除".equals(text)){
            String[] selectedRaceTimes = mainRaceTimeView.getSelectedRaceTimes();
            if(selectedRaceTimes.length==0){
                JOptionPane.showMessageDialog(mainRaceTimeView,"请选择要删除的行!");
                return;
            }
            int option = JOptionPane.showConfirmDialog(mainRaceTimeView,"确认删除所选的"
                        +selectedRaceTimes.length+"行吗？","确认删除",
                        JOptionPane.YES_NO_OPTION);
            if(option == JOptionPane.YES_OPTION){
                //执行删除
                RaceTimeService raceTimeService = new RaceTimeServiceImpl();
                boolean deleteResult = raceTimeService.delete(selectedRaceTimes);
                if(deleteResult){
                    //重新加载
                    mainRaceTimeView.reloadTable();
                }else {
                    JOptionPane.showMessageDialog(mainRaceTimeView,"删除失败!");
                }
            }
        }else if("(按比赛编号)查询".equals(text)){
            mainRaceTimeView.setPageNow(1);
            mainRaceTimeView.reloadTable();
        }else if("上一页".equals(text)){
            mainRaceTimeView.setPageNow(mainRaceTimeView.getPageNow() - 1);
            mainRaceTimeView.reloadTable();
        }else if("下一页".equals(text)){
            mainRaceTimeView.setPageNow(mainRaceTimeView.getPageNow() + 1);
            mainRaceTimeView.reloadTable();
        }
    }


}
