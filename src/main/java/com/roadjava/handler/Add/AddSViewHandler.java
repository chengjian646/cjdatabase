package com.roadjava.handler.Add;

import com.roadjava.entity.SDo;
import com.roadjava.service.interf.StudentSService;
import com.roadjava.service.impl.StudentSServiceImpl;
import com.roadjava.view.Add.AddSView;
import com.roadjava.view.Main.MainStudentSView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

public class AddSViewHandler extends KeyAdapter implements ActionListener {
    private AddSView addSView;
    private MainStudentSView mainStudentSView;
    public AddSViewHandler(AddSView addSView, MainStudentSView mainStudentSView){
        this.addSView=addSView;
        this.mainStudentSView=mainStudentSView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("添加".equals(text)){
            StudentSService studentSService =  new StudentSServiceImpl();
            SDo sDo = addSView.bulidSDo();
            if(!sDo.isEffective()){
                return;
            }
            boolean addResult = studentSService.add(sDo,addSView);
            if(addResult){
                mainStudentSView.reloadTable();
                addSView.dispose();//销毁窗口
            }else{
                JOptionPane.showMessageDialog(addSView,"添加失败！");
            }
        }
    }


}
