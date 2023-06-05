package com.roadjava.handler;

import com.roadjava.entity.JDo;
import com.roadjava.service.JudgeService;
import com.roadjava.service.impl.JudgeServiceImpl;
import com.roadjava.student.view.MainJudgeView;
import com.roadjava.student.view.UpdateJudgeView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

public class UpdateJudgeViewHandler extends KeyAdapter implements ActionListener {
    private UpdateJudgeView updateJudgeView;
    private MainJudgeView mainJudgeView;
    public UpdateJudgeViewHandler(UpdateJudgeView updateJudgeView, MainJudgeView mainJudgeView){
        this.updateJudgeView=updateJudgeView;
        this.mainJudgeView=mainJudgeView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("修改".equals(text)){
            JudgeService judgeService =  new JudgeServiceImpl();
            JDo jDo = updateJudgeView.bulidJDo();
            boolean updateResult = judgeService.update(jDo);
            if(updateResult){
                mainJudgeView.reloadTable();
                updateJudgeView.dispose();//销毁窗口
            }else{
                JOptionPane.showMessageDialog(updateJudgeView,"修改失败！");
            }
        }
    }


}
