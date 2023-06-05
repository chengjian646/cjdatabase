package com.roadjava.student.view.Add;

import com.roadjava.entity.RaceDo;
import com.roadjava.handler.Add.AddRaceViewHandler;
import com.roadjava.student.view.Main.MainRaceView;

import javax.swing.*;
import java.awt.*;

public class AddRaceView extends JDialog {
    JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,20));
    JLabel RnoLabel = new JLabel("比赛编号:",JLabel.RIGHT);
    JTextField RnoTxt = new JTextField();
    JLabel RnameLabel = new JLabel("比赛名称:",JLabel.RIGHT);
    JTextField RnameTxt = new JTextField();
    JLabel RpnoLabel = new JLabel("先决比赛编号:",JLabel.RIGHT);
    JTextField RpnoTxt = new JTextField();
    
    JButton AddBtn = new JButton("添加");
    AddRaceViewHandler addRaceViewHandler;

    public AddRaceView(MainRaceView mainRaceView){
        super(mainRaceView,"添加比赛信息记录",true);

        addRaceViewHandler = new AddRaceViewHandler(this,mainRaceView);



        RnoLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(RnoLabel);
        RnoTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(RnoTxt);

        RnameLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(RnameLabel);
        RnameTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(RnameTxt);

        RpnoLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(RpnoLabel);
        RpnoTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(RpnoTxt);

        AddBtn.addActionListener(addRaceViewHandler);
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
    public RaceDo bulidRaceDo(){
        RaceDo raceDo = new RaceDo();
        raceDo.setRno(RnoTxt.getText());
        raceDo.setRname(RnameTxt.getText());
        raceDo.setRpno(RpnoTxt.getText());
        return raceDo;
    }
}
