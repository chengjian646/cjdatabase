package com.roadjava.handler;

import com.roadjava.entity.AdminDO;
import com.roadjava.service.interf.AdminService;
import com.roadjava.service.impl.AdminServiceImpl;
import com.roadjava.view.ChooseTableView;
import com.roadjava.view.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginHandler extends KeyAdapter implements ActionListener {
    private LoginView loginView;
    private String Identity;
    public LoginHandler(LoginView loginView){
        this.loginView=loginView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("重置".equals(text)){
            loginView.getUserTxt().setText("");
            loginView.getPwdField().setText("");
        }else if("登录".equals(text)){

            if(loginView.getManagerButton().isSelected()){
                Identity=loginView.getManagerButton().getText();
            }else if(loginView.getstudentLoginButton().isSelected()){
                Identity=loginView.getstudentLoginButton().getText();
            }else if(loginView.getJudgementButton().isSelected()){
                Identity=loginView.getJudgementButton().getText();
            }else{
                JOptionPane.showMessageDialog(loginView,"请选择你的身份!");
                return;
            }
            login();
        }
    }

    private void login() {
        String user = loginView.getUserTxt().getText();
        char[] chars = loginView.getPwdField().getPassword();
        if(user==null||"".equals(user.trim())||chars == null){
            JOptionPane.showMessageDialog(loginView,"用户名或密码必填");
            return ;
        }

        String pwd = new String(chars);
        System.out.println(user+":"+pwd);
        //查询db
        AdminService adminService= new AdminServiceImpl();
        AdminDO adminDO = new AdminDO();//adminDo里有个属性是身份
        adminDO.setUserName(user);
        adminDO.setPwd(pwd);
        adminDO.setIdentity(Identity);
        boolean flag=adminService.validateAdmin(adminDO);
        if(flag){
            //跳转到主界面并销毁登录界面
            new ChooseTableView(Identity);
            loginView.dispose();
        }else{
            JOptionPane.showMessageDialog(loginView,"用户名或密码错误");
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(KeyEvent.VK_ENTER==e.getKeyCode()){
            login();
        }
    }
}
