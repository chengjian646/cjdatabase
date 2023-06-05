package com.roadjava.student.view.Add;

import com.roadjava.entity.RaceTimeDo;
import com.roadjava.handler.Add.AddRaceTimeViewHandler;
import com.roadjava.student.view.Main.MainRaceTimeView;

import javax.swing.*;
import java.awt.*;
import java.sql.Timestamp;

public class AddRaceTimeView extends JDialog {
    JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,20));
    JLabel RnoLabel = new JLabel("比赛编号:",JLabel.RIGHT);
    JTextField RnoTxt = new JTextField();
    JLabel TimeLabel = new JLabel("比赛时间:",JLabel.RIGHT);
    JTextField TimeTxt = new JTextField();
    JLabel RplaceLabel = new JLabel("比赛地点:",JLabel.RIGHT);
    JTextField RplaceTxt = new JTextField();
    JLabel JudgenoLabel = new JLabel("裁判编号:",JLabel.RIGHT);
    JTextField JudgenoTxt = new JTextField();
    
    JButton AddBtn = new JButton("添加");
    AddRaceTimeViewHandler addRaceTimeViewHandler;

    public AddRaceTimeView(MainRaceTimeView mainRaceTimeView){
        super(mainRaceTimeView,"添加比赛信息记录",true);

        addRaceTimeViewHandler = new AddRaceTimeViewHandler(this,mainRaceTimeView);



        RnoLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(RnoLabel);
        RnoTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(RnoTxt);

        TimeLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(TimeLabel);
        TimeTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(TimeTxt);

        RplaceLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(RplaceLabel);
        RplaceTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(RplaceTxt);

        JudgenoLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(JudgenoLabel);
        JudgenoTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(JudgenoTxt);

        AddBtn.addActionListener(addRaceTimeViewHandler);
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
    public RaceTimeDo bulidRaceTimeDo(){
        RaceTimeDo raceTimeDo = new RaceTimeDo();
        raceTimeDo.setRno(RnoTxt.getText());
        raceTimeDo.setRtimestamp(Timestamp.valueOf(TimeTxt.getText()));
        raceTimeDo.setRplace(RplaceTxt.getText());
        raceTimeDo.setJno(JudgenoTxt.getText());
        return raceTimeDo;
    }
}
