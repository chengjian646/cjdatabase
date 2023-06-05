package com.roadjava.handler.Main;

import com.roadjava.service.RaceService;
import com.roadjava.service.impl.RaceServiceImpl;
import com.roadjava.student.view.Add.AddRaceView;
import com.roadjava.student.view.Main.MainRaceView;
import com.roadjava.student.view.Update.UpdateRaceView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

public class MainRaceViewHandler extends KeyAdapter implements ActionListener {
    private MainRaceView mainRaceView;
    public MainRaceViewHandler(MainRaceView mainRaceView){
        this.mainRaceView=mainRaceView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("增加".equals(text)){
            new AddRaceView(mainRaceView);
        }else if("修改".equals(text)){
            String[] selectedRaces = mainRaceView.getSelectedRaces();
            if(selectedRaces.length==0){
                JOptionPane.showMessageDialog(mainRaceView,"请选择一行!");
                return;
            }
            if(selectedRaces.length!=1){
                JOptionPane.showMessageDialog(mainRaceView,"一次只能修改一行!");
                return;
            }
            new UpdateRaceView(mainRaceView,selectedRaces[0]);
        }else if("删除".equals(text)){
            String[] selectedRaces = mainRaceView.getSelectedRaces();
            if(selectedRaces.length==0){
                JOptionPane.showMessageDialog(mainRaceView,"请选择要删除的行!");
                return;
            }
            int option = JOptionPane.showConfirmDialog(mainRaceView,"确认删除所选的"
                        +selectedRaces.length+"行吗？","确认删除",
                        JOptionPane.YES_NO_OPTION);
            if(option == JOptionPane.YES_OPTION){
                //执行删除
                RaceService raceService = new RaceServiceImpl();
                boolean deleteResult = raceService.delete(selectedRaces);
                if(deleteResult){
                    //重新加载
                    mainRaceView.reloadTable();
                }else {
                    JOptionPane.showMessageDialog(mainRaceView,"删除失败!");
                }
            }
        }else if("查询".equals(text)){
            mainRaceView.setPageNow(1);
            mainRaceView.reloadTable();
        }else if("上一页".equals(text)){
            mainRaceView.setPageNow(mainRaceView.getPageNow() - 1);
            mainRaceView.reloadTable();
        }else if("下一页".equals(text)){
            mainRaceView.setPageNow(mainRaceView.getPageNow() + 1);
            mainRaceView.reloadTable();
        }
    }


}
