package com.roadjava.student.view.Update;

import com.roadjava.entity.RaceDo;
import com.roadjava.handler.Update.UpdateRaceViewHandler;
import com.roadjava.service.RaceService;
import com.roadjava.service.impl.RaceServiceImpl;
import com.roadjava.student.view.Main.MainRaceView;

import javax.swing.*;
import java.awt.*;

public class UpdateRaceView extends JDialog {
    JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,20));
    JLabel RnoLabel = new JLabel("比赛编号:",JLabel.RIGHT);
    JTextField RnoTxt = new JTextField();
    JLabel RnameLabel = new JLabel("比赛名称:",JLabel.RIGHT);
    JTextField RnameTxt = new JTextField();
    JLabel RpnoLabel = new JLabel("先决比赛编号:",JLabel.RIGHT);
    JTextField RpnoTxt = new JTextField();
    JButton UpdateBtn = new JButton("修改");

    UpdateRaceViewHandler updateRaceViewHandler;
    public UpdateRaceView(MainRaceView mainRaceView, String selectedRace){
        super(mainRaceView,"修改比赛项目信息",true);

        updateRaceViewHandler=new UpdateRaceViewHandler(this,mainRaceView);

        //查询selectedRecordsId对应的记录并回显
        RaceService raceService = new RaceServiceImpl();
        RaceDo selRace = raceService.getById(selectedRace);

        RnoLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(RnoLabel);
        RnoTxt.setPreferredSize(new Dimension(200,30));
        RnoTxt.setText(selRace.getRno());
        RnoTxt.setEnabled(false);//设置此项不可编辑
        jPanel.add(RnoTxt);

        RnameLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(RnameLabel);
        RnameTxt.setPreferredSize(new Dimension(200,30));
        RnameTxt.setText(String.valueOf(selRace.getRname()));
        jPanel.add(RnameTxt);

        RpnoLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(RpnoLabel);
        RpnoTxt.setPreferredSize(new Dimension(200,30));
        RpnoTxt.setText(selRace.getRpno());
        jPanel.add(RpnoTxt);

        UpdateBtn.addActionListener(updateRaceViewHandler);
        jPanel.add(UpdateBtn);

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

    //获取修改后的记录对象
    public RaceDo bulidUpdatedRaceDo() {
        RaceDo raceDo = new RaceDo();
        raceDo.setRpno(RpnoTxt.getText());
        raceDo.setRno(RnoTxt.getText());
        raceDo.setRname(RnameTxt.getText());
        return raceDo;
    }
}
