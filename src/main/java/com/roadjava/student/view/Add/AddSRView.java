package com.roadjava.student.view.Add;

import com.roadjava.entity.SRDo;
import com.roadjava.handler.Add.AddSRViewHandler;
import com.roadjava.student.view.Main.MainView;

import javax.swing.*;
import java.awt.*;

public class AddSRView extends JDialog {
    JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,20));
    JLabel SnoLabel = new JLabel("学号:",JLabel.RIGHT);
    JTextField SnoTxt = new JTextField();
    JLabel RnoLabel = new JLabel("比赛编号:",JLabel.RIGHT);
    JTextField RnoTxt = new JTextField();
    JLabel GradeLabel = new JLabel("成绩:",JLabel.RIGHT);
    JTextField GradeTxt = new JTextField();
    JButton AddBtn = new JButton("添加");
    AddSRViewHandler addSRViewHandler;

    public AddSRView(MainView mainView){
        super(mainView,"添加成绩记录",true);

        addSRViewHandler = new AddSRViewHandler(this,mainView);

        SnoLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(SnoLabel);
        SnoTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(SnoTxt);

        RnoLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(RnoLabel);
        RnoTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(RnoTxt);

        GradeLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(GradeLabel);
        GradeTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(GradeTxt);

        AddBtn.addActionListener(addSRViewHandler);
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
    public SRDo bulidSRDo(){
        SRDo srDo = new SRDo();
        srDo.setSno(SnoTxt.getText());
        srDo.setRno(RnoTxt.getText());
        srDo.setGrade(Integer.valueOf(GradeTxt.getText()));
        return srDo;
    }
}
