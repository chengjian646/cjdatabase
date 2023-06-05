package com.roadjava.student.view.Update;

import com.roadjava.entity.JDo;
import com.roadjava.handler.Update.UpdateJudgeViewHandler;
import com.roadjava.service.JudgeService;
import com.roadjava.service.impl.JudgeServiceImpl;
import com.roadjava.student.view.Main.MainJudgeView;

import javax.swing.*;
import java.awt.*;

public class UpdateJudgeView extends JDialog {
    JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,20));
    JLabel JnoLabel = new JLabel("工号:",JLabel.RIGHT);
    JTextField JnoTxt = new JTextField();
    JLabel JnameLabel = new JLabel("姓名:",JLabel.RIGHT);
    JTextField JnameTxt = new JTextField();
    JButton AddBtn = new JButton("修改");
    UpdateJudgeViewHandler updateJudgeViewHandler;

    public UpdateJudgeView(MainJudgeView mainJudgeView, String selectedJudge){
        super(mainJudgeView,"修改裁判信息",true);

        updateJudgeViewHandler = new UpdateJudgeViewHandler(this,mainJudgeView);
        
        //查询selectedJudge对应的记录并回显
        JudgeService judgeService = new JudgeServiceImpl();
        JDo selStu = judgeService.getById(selectedJudge);

        JnoLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(JnoLabel);
        JnoTxt.setPreferredSize(new Dimension(200,30));
        JnoTxt.setText(selStu.getJno());
        JnoTxt.setEnabled(false);//此项不可编辑
        jPanel.add(JnoTxt);

        JnameLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(JnameLabel);
        JnameTxt.setPreferredSize(new Dimension(200,30));
        JnameTxt.setText(selStu.getJname());
        jPanel.add(JnameTxt);
        

        AddBtn.addActionListener(updateJudgeViewHandler);
        jPanel.add(AddBtn);

        Container contentPane = getContentPane();
        contentPane.add(jPanel);

        setSize(350,500);
        //设置居中显示
        setLocationRelativeTo(null);
        //关闭时只销毁当前对话框
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //窗口大小可改变
        setResizable(true);
        setVisible(true);
    }
    public JDo bulidJDo(){
        JDo jDo = new JDo();
        jDo.setJno(JnoTxt.getText());
        jDo.setJname(JnameTxt.getText());
        return jDo;
    }
}
