package com.roadjava.handler;

import com.roadjava.entity.SRDo;
import com.roadjava.service.StudentService;
import com.roadjava.service.impl.StudentServiceImpl;
import com.roadjava.student.view.AddSRView;
import com.roadjava.student.view.MainView;
import com.roadjava.student.view.UpdateSRView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

public class UpdateSRViewHandler extends KeyAdapter implements ActionListener {
    private UpdateSRView updateSRView;
    private MainView mainView;
    public UpdateSRViewHandler(UpdateSRView updateSRView, MainView mainView){
        this.updateSRView=updateSRView;
        this.mainView=mainView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("修改".equals(text)){
            StudentService studentService =  new StudentServiceImpl();
            SRDo srDo = updateSRView.bulidUpdatedSRDo();
            boolean updateResult = studentService.update(srDo);
            if(updateResult){
                mainView.reloadTable();
                updateSRView.dispose();//销毁窗口
            }else{
                JOptionPane.showMessageDialog(updateSRView,"修改失败！");
            }
        }
    }


}
