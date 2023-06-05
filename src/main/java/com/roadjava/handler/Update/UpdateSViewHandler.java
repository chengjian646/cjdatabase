package com.roadjava.handler.Update;

import com.roadjava.entity.SDo;
import com.roadjava.service.interf.StudentSService;
import com.roadjava.service.impl.StudentSServiceImpl;
import com.roadjava.student.view.Main.MainStudentSView;
import com.roadjava.student.view.Update.UpdateSView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

public class UpdateSViewHandler extends KeyAdapter implements ActionListener {
    private UpdateSView updateSView;
    private MainStudentSView mainStudentSView;
    public UpdateSViewHandler(UpdateSView updateSView, MainStudentSView mainStudentSView){
        this.updateSView=updateSView;
        this.mainStudentSView=mainStudentSView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("修改".equals(text)){
            StudentSService studentSService =  new StudentSServiceImpl();
            SDo sDo = updateSView.bulidSDo();
            boolean updateResult = studentSService.update(sDo);
            if(updateResult){
                mainStudentSView.reloadTable();
                updateSView.dispose();//销毁窗口
            }else{
                JOptionPane.showMessageDialog(updateSView,"修改失败！");
            }
        }
    }


}
