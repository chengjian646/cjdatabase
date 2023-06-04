package com.roadjava.student.view;

import com.roadjava.handler.ChooseTableHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

public class ChooseTableView extends JFrame {
    JLabel nameLabel = new JLabel("选择表",JLabel.CENTER);

    JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));


    JButton StuRaceBtn = new JButton("选赛表");
    JButton RaceBtn = new JButton("比赛项目表");
    JButton RaceTimeBtn = new JButton("赛程表");
    JButton StuBtn = new JButton("学生信息表");
    JButton JudgeBtn = new JButton("裁判信息表");
    SystemTray systemTray;
    TrayIcon trayIcon;

    ChooseTableHandler chooseTableHandler;


    public ChooseTableView(String identity) {
        super("运动会信息管理系统");

        chooseTableHandler = new ChooseTableHandler(this);

        Container contentPane=getContentPane();

        nameLabel.setFont(new Font("华文行楷",Font.PLAIN,40));
        nameLabel.setPreferredSize(new Dimension(0,80));

        Font centerFont = new Font("楷体",Font.PLAIN,20);
        StuRaceBtn.setFont(centerFont);
        RaceBtn.setFont(centerFont);
        RaceTimeBtn.setFont(centerFont);
        StuBtn.setFont(centerFont);
        JudgeBtn.setFont(centerFont);
        //把组件加入面板
        contentPane.add(nameLabel,BorderLayout.NORTH);

        StuRaceBtn.addActionListener(chooseTableHandler);
        centerPanel.add(StuRaceBtn);
        RaceBtn.addActionListener(chooseTableHandler);
        centerPanel.add(RaceBtn);
        RaceTimeBtn.addActionListener(chooseTableHandler);
        centerPanel.add(RaceTimeBtn);
        if(identity.equals("judgement")){
            StuBtn.addActionListener(chooseTableHandler);
            centerPanel.add(StuBtn);
        }else if(identity.equals("manager")){
            StuBtn.addActionListener(chooseTableHandler);
            centerPanel.add(StuBtn);
            JudgeBtn.addActionListener(chooseTableHandler);
            centerPanel.add(JudgeBtn);
        }

        contentPane.add(centerPanel,BorderLayout.CENTER);

        URL imgUrl = ChooseTableView.class.getClassLoader().getResource("ice.png");
        setIconImage(new ImageIcon(imgUrl).getImage());
        setSize(600,300);
        //设置居中显示
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        int offsetX = (screenSize.width - 600) / 2;
        int offsetY = (screenSize.height - 400) / 2;
        setLocation(offsetX,offsetY);
        //关闭时退出程序
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //窗口大小不可改变
        setResizable(false);
        setVisible(true);
    }


}
