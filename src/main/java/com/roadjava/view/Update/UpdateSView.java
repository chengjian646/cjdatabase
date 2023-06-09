package com.roadjava.view.Update;

import com.roadjava.entity.SDo;
import com.roadjava.handler.Update.UpdateSViewHandler;
import com.roadjava.service.interf.StudentSService;
import com.roadjava.service.impl.StudentSServiceImpl;
import com.roadjava.view.Main.MainStudentSView;

import javax.swing.*;
import java.awt.*;

public class UpdateSView extends JDialog {
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
    JButton AddBtn = new JButton("修改");
    UpdateSViewHandler updateSViewHandler;

    public UpdateSView(MainStudentSView mainStudentSView, String selectedStu){
        super(mainStudentSView,"修改学生信息",true);

        updateSViewHandler = new UpdateSViewHandler(this,mainStudentSView);
        
        //查询selectedStu对应的记录并回显
        StudentSService studentSService = new StudentSServiceImpl();
        SDo selStu = studentSService.getById(selectedStu);

        SnoLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(SnoLabel);
        SnoTxt.setPreferredSize(new Dimension(200,30));
        SnoTxt.setText(selStu.getSno());
        SnoTxt.setEnabled(false);//此项不可编辑
        jPanel.add(SnoTxt);

        SnameLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(SnameLabel);
        SnameTxt.setPreferredSize(new Dimension(200,30));
        SnameTxt.setText(selStu.getSname());
        jPanel.add(SnameTxt);

        SgenderLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(SgenderLabel);
        SgenderTxt.setPreferredSize(new Dimension(200,30));
        SgenderTxt.setText(selStu.getSgender());
        jPanel.add(SgenderTxt);

        SageLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(SageLabel);
        SageTxt.setPreferredSize(new Dimension(200,30));
        SageTxt.setText(String.valueOf(selStu.getSage()));
        jPanel.add(SageTxt);

        SclassLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(SclassLabel);
        SclassTxt.setPreferredSize(new Dimension(200,30));
        SclassTxt.setText(selStu.getSclass());
        jPanel.add(SclassTxt);

        AddBtn.addActionListener(updateSViewHandler);
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
        if("M".equals(SgenderTxt.getText())||"W".equals(SgenderTxt.getText())){
            sDo.setSgender(SgenderTxt.getText());
        }else{
            sDo.setEffective(false);
            JOptionPane.showMessageDialog(this,"请输入正确的性别M/W!");
        }
        if(Integer.valueOf(SageTxt.getText())<0||Integer.valueOf(SageTxt.getText())>60){
            sDo.setEffective(false);
            JOptionPane.showMessageDialog(this,"请输入正确的年龄!");
        }else{
            sDo.setSage(Integer.valueOf(SageTxt.getText()));
        }
        sDo.setSclass(SclassTxt.getText());
        return sDo;
    }
}
