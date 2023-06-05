package com.roadjava.student.view.Update;

import com.roadjava.entity.RaceTimeDo;
import com.roadjava.handler.Update.UpdateRaceTimeViewHandler;
import com.roadjava.service.interf.RaceTimeService;
import com.roadjava.service.impl.RaceTimeServiceImpl;
import com.roadjava.student.view.Main.MainRaceTimeView;

import javax.swing.*;
import java.awt.*;
import java.sql.Timestamp;

public class UpdateRaceTimeView extends JDialog {
    JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,20));
    JLabel RnoLabel = new JLabel("比赛编号:",JLabel.RIGHT);
    JTextField RnoTxt = new JTextField();
    JLabel TimeLabel = new JLabel("比赛时间:",JLabel.RIGHT);
    JTextField TimeTxt = new JTextField();
    JLabel RplaceLabel = new JLabel("比赛地点:",JLabel.RIGHT);
    JTextField RplaceTxt = new JTextField();
    JLabel JudgenoLabel = new JLabel("裁判编号:",JLabel.RIGHT);
    JTextField JudgenoTxt = new JTextField();

    JButton AddBtn = new JButton("修改");
    UpdateRaceTimeViewHandler updateRaceTimeViewHandler;

    public UpdateRaceTimeView(MainRaceTimeView mainRaceTimeView, String selectedRaceTime){
        super(mainRaceTimeView,"修改比赛信息记录",true);

        updateRaceTimeViewHandler = new UpdateRaceTimeViewHandler(this,mainRaceTimeView);

        //查询selectedRecordsId对应的记录并回显
        RaceTimeService raceTimeService = new RaceTimeServiceImpl();
        RaceTimeDo selRace = raceTimeService.getById(selectedRaceTime);
        
        RnoLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(RnoLabel);
        RnoTxt.setPreferredSize(new Dimension(200,30));
        RnoTxt.setText(selRace.getRno());
        RnoTxt.setEnabled(false);
        jPanel.add(RnoTxt);

        TimeLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(TimeLabel);
        TimeTxt.setPreferredSize(new Dimension(200,30));
        TimeTxt.setText(String.valueOf(selRace.getRtimestamp()));
        jPanel.add(TimeTxt);

        RplaceLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(RplaceLabel);
        RplaceTxt.setPreferredSize(new Dimension(200,30));
        RplaceTxt.setText(selRace.getRplace());
        jPanel.add(RplaceTxt);

        JudgenoLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(JudgenoLabel);
        JudgenoTxt.setPreferredSize(new Dimension(200,30));
        JudgenoTxt.setText(selRace.getJno());
        jPanel.add(JudgenoTxt);

        AddBtn.addActionListener(updateRaceTimeViewHandler);
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
    public RaceTimeDo bulidUpdatedRaceTimeDo(){
        RaceTimeDo raceTimeDo = new RaceTimeDo();
        raceTimeDo.setRno(RnoTxt.getText());
        raceTimeDo.setRtimestamp(Timestamp.valueOf(TimeTxt.getText()));
        raceTimeDo.setRplace(RplaceTxt.getText());
        raceTimeDo.setJno(JudgenoTxt.getText());
        return raceTimeDo;
    }
}
