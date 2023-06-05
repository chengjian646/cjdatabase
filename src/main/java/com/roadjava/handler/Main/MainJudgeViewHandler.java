package com.roadjava.handler.Main;

import com.roadjava.service.JudgeService;
import com.roadjava.service.impl.JudgeServiceImpl;
import com.roadjava.student.view.Add.AddJudgeView;
import com.roadjava.student.view.Main.MainJudgeView;
import com.roadjava.student.view.Update.UpdateJudgeView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

public class MainJudgeViewHandler extends KeyAdapter implements ActionListener {
    private MainJudgeView mainJudgeView;
    public MainJudgeViewHandler(MainJudgeView mainJudgeView){
        this.mainJudgeView=mainJudgeView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("增加".equals(text)){
            new AddJudgeView(mainJudgeView);
        }else if("修改".equals(text)){
            String[] selectedJudge = mainJudgeView.getSelectedJudge();
            if(selectedJudge.length==0){
                JOptionPane.showMessageDialog(mainJudgeView,"请选择一行!");
                return;
            }
            if(selectedJudge.length!=1){
                JOptionPane.showMessageDialog(mainJudgeView,"一次只能修改一行!");
                return;
            }
            new UpdateJudgeView(mainJudgeView,selectedJudge[0]);
        }else if("删除".equals(text)){
            String[] selectedJudge = mainJudgeView.getSelectedJudge();
            if(selectedJudge.length==0){
                JOptionPane.showMessageDialog(mainJudgeView,"请选择要删除的行!");
                return;
            }
            int option = JOptionPane.showConfirmDialog(mainJudgeView,"确认删除所选的"
                        +selectedJudge.length+"行吗？","确认删除",
                        JOptionPane.YES_NO_OPTION);
            if(option == JOptionPane.YES_OPTION){
                //执行删除
                JudgeService judgeService = new JudgeServiceImpl();
                boolean deleteResult = judgeService.delete(selectedJudge);
                if(deleteResult){
                    //重新加载
                    mainJudgeView.reloadTable();
                }else {
                    JOptionPane.showMessageDialog(mainJudgeView,"删除失败!");
                }
            }
        }else if("(按工号)查询".equals(text)){
            mainJudgeView.setPageNow(1);
            mainJudgeView.reloadTable();
        }else if("上一页".equals(text)){
            mainJudgeView.setPageNow(mainJudgeView.getPageNow() - 1);
            mainJudgeView.reloadTable();
        }else if("下一页".equals(text)){
            mainJudgeView.setPageNow(mainJudgeView.getPageNow() + 1);
            mainJudgeView.reloadTable();
        }
    }


}
