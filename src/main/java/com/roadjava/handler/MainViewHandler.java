package com.roadjava.handler;

import com.roadjava.entity.AdminDO;
import com.roadjava.service.AdminService;
import com.roadjava.service.impl.AdminServiceImpl;
import com.roadjava.student.view.AddSRView;
import com.roadjava.student.view.LoginView;
import com.roadjava.student.view.MainView;
import com.roadjava.student.view.UpdateSRView;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainViewHandler extends KeyAdapter implements ActionListener {
    private MainView mainView;
    public MainViewHandler(MainView mainView){
        this.mainView=mainView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("增加".equals(text)){
            new AddSRView(mainView);
        }else if("修改".equals(text)){
            new UpdateSRView(mainView);
        }else if("删除".equals(text)){

        }else if("查询".equals(text)){
            mainView.setPageNow(1);
            mainView.reloadTable();
        }else if("上一页".equals(text)){
            mainView.setPageNow(mainView.getPageNow() - 1);
            mainView.reloadTable();
        }else if("下一页".equals(text)){
            mainView.setPageNow(mainView.getPageNow() + 1);
            mainView.reloadTable();
        }
    }


}
