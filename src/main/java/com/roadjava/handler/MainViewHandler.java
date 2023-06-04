package com.roadjava.handler;

import com.roadjava.entity.AdminDO;
import com.roadjava.entity.SelectSRPK;
import com.roadjava.service.AdminService;
import com.roadjava.service.StudentService;
import com.roadjava.service.impl.AdminServiceImpl;
import com.roadjava.service.impl.StudentServiceImpl;
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
            SelectSRPK selectSRPK = mainView.getSelectedRecordsIds();
            if(selectSRPK.getSno().length==0){
                JOptionPane.showMessageDialog(mainView,"请选择一行!");
                return;
            }
            if(selectSRPK.getSno().length!=1){
                JOptionPane.showMessageDialog(mainView,"一次只能修改一行!");
                return;
            }
            new UpdateSRView(mainView,selectSRPK);
        }else if("删除".equals(text)){
            SelectSRPK selectSRPK = mainView.getSelectedRecordsIds();
            if(selectSRPK.getSno().length==0){
                JOptionPane.showMessageDialog(mainView,"请选择要删除的行!");
                return;
            }
            int option = JOptionPane.showConfirmDialog(mainView,"确认删除所选的"
                        +selectSRPK.getSno().length+"行吗？","确认删除",
                        JOptionPane.YES_NO_OPTION);
            if(option == JOptionPane.YES_OPTION){
                //执行删除
                StudentService studentService = new StudentServiceImpl();
                boolean deleteResult = studentService.delete(selectSRPK);
                if(deleteResult){
                    //重新加载
                    mainView.reloadTable();
                }else {
                    JOptionPane.showMessageDialog(mainView,"删除失败!");
                }
            }
        }else if("(按学号)查询".equals(text)){
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
