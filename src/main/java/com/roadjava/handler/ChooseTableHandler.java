package com.roadjava.handler;

import com.roadjava.entity.SRDo;
import com.roadjava.service.StudentService;
import com.roadjava.service.impl.StudentServiceImpl;
import com.roadjava.student.view.AddSRView;
import com.roadjava.student.view.ChooseTableView;
import com.roadjava.student.view.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

public class ChooseTableHandler extends KeyAdapter implements ActionListener {
    private ChooseTableView chooseTableView;
    public ChooseTableHandler(ChooseTableView chooseTableView){
        this.chooseTableView=chooseTableView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("选赛表".equals(text)){
            new MainView();
            chooseTableView.dispose();
        }else if("比赛项目表".equals(text)){

        }else if("赛程表".equals(text)){

        }else if("学生信息表".equals(text)){

        }else if("裁判信息表".equals(text)){

        }
    }


}