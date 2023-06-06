package com.roadjava.handler.Main;

import com.roadjava.service.interf.StudentSService;
import com.roadjava.service.impl.StudentSServiceImpl;
import com.roadjava.view.Add.AddSView;
import com.roadjava.view.Main.MainStudentSView;
import com.roadjava.view.Update.UpdateSView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

public class MainStudentSViewHandler extends KeyAdapter implements ActionListener {
    private MainStudentSView mainStudentSView;
    public MainStudentSViewHandler(MainStudentSView mainStudentSView){
        this.mainStudentSView=mainStudentSView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("增加".equals(text)){
            new AddSView(mainStudentSView);
        }else if("修改".equals(text)){
            String[] selectedStu = mainStudentSView.getSelectedStu();
            if(selectedStu.length==0){
                JOptionPane.showMessageDialog(mainStudentSView,"请选择一行!");
                return;
            }
            if(selectedStu.length!=1){
                JOptionPane.showMessageDialog(mainStudentSView,"一次只能修改一行!");
                return;
            }
            new UpdateSView(mainStudentSView,selectedStu[0]);
        }else if("删除".equals(text)){
            String[] selectedStu = mainStudentSView.getSelectedStu();
            if(selectedStu.length==0){
                JOptionPane.showMessageDialog(mainStudentSView,"请选择要删除的行!");
                return;
            }
            int option = JOptionPane.showConfirmDialog(mainStudentSView,"确认删除所选的"
                        +selectedStu.length+"行吗？","确认删除",
                        JOptionPane.YES_NO_OPTION);
            if(option == JOptionPane.YES_OPTION){
                //执行删除
                StudentSService studentSService = new StudentSServiceImpl();
                boolean deleteResult = studentSService.delete(selectedStu,mainStudentSView);
                if(deleteResult){
                    //重新加载
                    mainStudentSView.reloadTable();
                }else {
                    JOptionPane.showMessageDialog(mainStudentSView,"删除失败!");
                }
            }
        }else if("查询".equals(text)){
            mainStudentSView.setPageNow(1);
            mainStudentSView.reloadTable();
        }else if("上一页".equals(text)){
            mainStudentSView.setPageNow(mainStudentSView.getPageNow() - 1);
            mainStudentSView.reloadTable();
        }else if("下一页".equals(text)){
            mainStudentSView.setPageNow(mainStudentSView.getPageNow() + 1);
            mainStudentSView.reloadTable();
        }
    }


}
