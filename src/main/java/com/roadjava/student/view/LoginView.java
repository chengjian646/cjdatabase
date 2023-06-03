package com.roadjava.student.view;

import com.roadjava.handler.LoginHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

public class LoginView extends JFrame {
    JLabel nameLabel = new JLabel("登录系统",JLabel.CENTER);

    SpringLayout springLayout = new SpringLayout();
    JPanel centerPanel = new JPanel(springLayout);//弹簧布局

    JLabel userNameLabel=new JLabel("用户名：");
    JTextField userTxt = new JTextField();

    JLabel pwdLabel = new JLabel("密码：");
    JPasswordField pwdField =new JPasswordField();

    JButton loginBtn = new JButton("登录");
    JButton resetBtn = new JButton("重置");

    SystemTray systemTray;
    TrayIcon trayIcon;

    LoginHandler loginHandler;
    public LoginView() {
        super("运动会信息管理系统");

        loginHandler = new LoginHandler(this);

        Container contentPane=getContentPane();

        nameLabel.setFont(new Font("华文行楷",Font.PLAIN,40));
        nameLabel.setPreferredSize(new Dimension(0,80));

        Font centerFont = new Font("楷体",Font.PLAIN,20);
        userNameLabel.setFont(centerFont);
        userTxt.setPreferredSize(new Dimension(200,30));
        pwdLabel.setFont(centerFont);
        pwdField.setPreferredSize(new Dimension(200,30));
        loginBtn.setFont(centerFont);
        resetBtn.setFont(centerFont);
        //把组件加入面板
        centerPanel.add(userNameLabel);
        centerPanel.add(userTxt);
        centerPanel.add(pwdLabel);
        centerPanel.add(pwdField);
        loginBtn.addActionListener(loginHandler);
        loginBtn.addKeyListener(loginHandler);//按回车，登录
        centerPanel.add(loginBtn);
        resetBtn.addActionListener(loginHandler);
        centerPanel.add(resetBtn);
        //弹簧布局
        layoutCenter();

        contentPane.add(nameLabel,BorderLayout.NORTH);
        contentPane.add(centerPanel,BorderLayout.CENTER);


        if(SystemTray.isSupported()){
            systemTray=SystemTray.getSystemTray();
            URL imgUrl = LoginView.class.getClassLoader().getResource("nuaa.png");
            trayIcon=new TrayIcon(new ImageIcon(imgUrl).getImage());
            trayIcon.setImageAutoSize(true);
            try {
                systemTray.add(trayIcon);
            } catch (AWTException e) {
                e.printStackTrace();
            }
            //最小化时销毁资源
            this.addWindowListener(new WindowAdapter() {
                @Override
                public void windowIconified(WindowEvent e) {
                    LoginView.this.dispose();
                }
            });
            //托盘事件监听
            trayIcon.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int clickCount = e.getClickCount();
                    if(clickCount==1){
                        LoginView.this.setExtendedState(JFrame.NORMAL);
                    }
                    LoginView.this.setVisible(true);
                }
            });
        }

        //设置loginBtn为默认按钮（为了回车触发登录）
        getRootPane().setDefaultButton(loginBtn);
        URL imgUrl = LoginView.class.getClassLoader().getResource("ice.png");
        setIconImage(new ImageIcon(imgUrl).getImage());
        setSize(600,400);
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

    private void layoutCenter() {
        //开始弹簧布局
        Spring childWidth = Spring.sum(Spring.sum(Spring.width(userNameLabel),Spring.width(userTxt)),
                Spring.constant(20));
        int offsetx = childWidth.getValue() / 2;
        springLayout.putConstraint(SpringLayout.WEST,userNameLabel,-offsetx,
                SpringLayout.HORIZONTAL_CENTER,centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH,userNameLabel,20,SpringLayout.NORTH,centerPanel);
        //userTxt
        springLayout.putConstraint(SpringLayout.WEST,userTxt,20,SpringLayout.EAST,userNameLabel);
        springLayout.putConstraint(SpringLayout.NORTH,userTxt,0,SpringLayout.NORTH,userNameLabel);
        //pwdLabel
        springLayout.putConstraint(SpringLayout.EAST,pwdLabel,0,SpringLayout.EAST,userNameLabel);
        springLayout.putConstraint(SpringLayout.NORTH,pwdLabel,10,SpringLayout.SOUTH,userNameLabel);
        //pwdField
        springLayout.putConstraint(SpringLayout.WEST,pwdField,20,SpringLayout.EAST,pwdLabel);
        springLayout.putConstraint(SpringLayout.NORTH,pwdField,0,SpringLayout.NORTH,pwdLabel);
        //loginBtn
        springLayout.putConstraint(SpringLayout.WEST,loginBtn,55,SpringLayout.WEST,pwdLabel);
        springLayout.putConstraint(SpringLayout.NORTH,loginBtn,20,SpringLayout.SOUTH,pwdLabel);
        //resetBtn
        springLayout.putConstraint(SpringLayout.WEST,resetBtn,55,SpringLayout.EAST,loginBtn);
        springLayout.putConstraint(SpringLayout.NORTH,resetBtn,0,SpringLayout.NORTH,loginBtn);
    }

    public static void main(String[] args) {
        new LoginView();
    }

    public JTextField getUserTxt() {
        return userTxt;
    }

    public JPasswordField getPwdField() {
        return pwdField;
    }

}
