package com.roadjava.student.view.Add;

import com.roadjava.entity.SDo;
import com.roadjava.handler.Add.AddSViewHandler;
import com.roadjava.student.view.Main.MainStudentSView;

import javax.swing.*;
import java.awt.*;

public class AddSView extends JDialog {
    JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,20));
    JLabel SnoLabel = new JLabel("学号:",JLabel.RIGHT);
    JTextField SnoTxt = new JTextField();
    JLabel SnameLabel = new JLabel("姓名:",JLabel.RIGHT);
    JTextField SnameTxt = new JTextField();
    JLabel SgenderLabel = new JLabel("性别",JLabel.RIGHT);
    JTextField SgenderTxt = new JTextField();
    JLabel SageLabel = new JLabel("年龄:",JLabel.RIGHT);
    JTextField SageTxt = new JTextField();
    JLabel SclassLabel = new JLabel("班级",JLabel.RIGHT);
    JTextField SclassTxt = new JTextField();
    JButton AddBtn = new JButton("添加");
    AddSViewHandler addSViewHandler;

    public AddSView(MainStudentSView mainStudentSView){
        super(mainStudentSView,"添加学生信息",true);

        addSViewHandler = new AddSViewHandler(this,mainStudentSView);

        SnoLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(SnoLabel);
        SnoTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(SnoTxt);

        SnameLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(SnameLabel);
        SnameTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(SnameTxt);

        SgenderLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(SgenderLabel);
        SgenderTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(SgenderTxt);

        SageLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(SageLabel);
        SageTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(SageTxt);

        SclassLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(SclassLabel);
        SclassTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(SclassTxt);

        AddBtn.addActionListener(addSViewHandler);
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
    public SDo bulidSDo(){
        SDo sDo = new SDo();
        sDo.setSno(SnoTxt.getText());
        sDo.setSname(SnameTxt.getText());
        sDo.setSgender(SgenderTxt.getText());
        sDo.setSage(Integer.valueOf(SageTxt.getText()));
        sDo.setSclass(SclassTxt.getText());
        return sDo;
    }
}
