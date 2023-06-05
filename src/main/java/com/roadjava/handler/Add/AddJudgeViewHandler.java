package com.roadjava.handler.Add;

import com.roadjava.entity.JDo;
import com.roadjava.service.interf.JudgeService;
import com.roadjava.service.impl.JudgeServiceImpl;
import com.roadjava.student.view.Add.AddJudgeView;
import com.roadjava.student.view.Main.MainJudgeView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

public class AddJudgeViewHandler extends KeyAdapter implements ActionListener {
    private AddJudgeView addJudgeView;
    private MainJudgeView mainJudgeView;
    public AddJudgeViewHandler(AddJudgeView addJudgeView, MainJudgeView mainJudgeView){
        this.addJudgeView=addJudgeView;
        this.mainJudgeView=mainJudgeView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("添加".equals(text)){
            JudgeService judgeService =  new JudgeServiceImpl();
            JDo jDo = addJudgeView.bulidJDo();
            boolean addResult = judgeService.add(jDo);
            if(addResult){
                mainJudgeView.reloadTable();
                addJudgeView.dispose();//销毁窗口
            }else{
                JOptionPane.showMessageDialog(addJudgeView,"添加失败！");
            }
        }
    }


}
