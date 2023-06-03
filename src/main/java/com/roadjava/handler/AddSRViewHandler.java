package com.roadjava.handler;

import com.roadjava.entity.SRDo;
import com.roadjava.service.StudentService;
import com.roadjava.service.impl.StudentServiceImpl;
import com.roadjava.student.view.AddSRView;
import com.roadjava.student.view.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

public class AddSRViewHandler extends KeyAdapter implements ActionListener {
    private AddSRView addSRView;
    private MainView mainView;
    public AddSRViewHandler(AddSRView addSRView, MainView mainView){
        this.addSRView=addSRView;
        this.mainView=mainView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("添加".equals(text)){
            StudentService studentService =  new StudentServiceImpl();
            SRDo srDo = addSRView.bulidSRDo();
            boolean addResult = studentService.add(srDo);
            if(addResult){
                mainView.reloadTable();
                addSRView.dispose();//销毁窗口
            }else{
                JOptionPane.showMessageDialog(addSRView,"添加失败！");
            }
        }
    }


}
