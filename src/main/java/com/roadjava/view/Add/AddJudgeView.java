package com.roadjava.view.Add;

import com.roadjava.entity.JDo;
import com.roadjava.handler.Add.AddJudgeViewHandler;
import com.roadjava.view.Main.MainJudgeView;

import javax.swing.*;
import java.awt.*;

public class AddJudgeView extends JDialog {
    JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,20));
    JLabel JnoLabel = new JLabel("工号:",JLabel.RIGHT);
    JTextField JnoTxt = new JTextField();
    JLabel JnameLabel = new JLabel("姓名:",JLabel.RIGHT);
    JTextField JnameTxt = new JTextField();
    JButton AddBtn = new JButton("添加");
    AddJudgeViewHandler addJudgeViewHandler;

    public AddJudgeView(MainJudgeView mainJudgeView){
        super(mainJudgeView,"添加裁判信息",true);

        addJudgeViewHandler = new AddJudgeViewHandler(this,mainJudgeView);

        JnoLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(JnoLabel);
        JnoTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(JnoTxt);

        JnameLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(JnameLabel);
        JnameTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(JnameTxt);
        

        AddBtn.addActionListener(addJudgeViewHandler);
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
