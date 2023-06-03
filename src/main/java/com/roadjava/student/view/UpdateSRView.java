package com.roadjava.student.view;

import com.roadjava.entity.SRDo;
import com.roadjava.entity.SelectSRPK;
import com.roadjava.handler.UpdateSRViewHandler;
import com.roadjava.service.StudentService;
import com.roadjava.service.impl.StudentServiceImpl;

import javax.swing.*;
import java.awt.*;

public class UpdateSRView extends JDialog {
    JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,20));
    JLabel SnoLabel = new JLabel("学号:",JLabel.RIGHT);
    JTextField SnoTxt = new JTextField();
    JLabel RnoLabel = new JLabel("项目编号:",JLabel.RIGHT);
    JTextField RnoTxt = new JTextField();
    JLabel GradeLabel = new JLabel("成绩:",JLabel.RIGHT);
    JTextField GradeTxt = new JTextField();
    JButton UpdateBtn = new JButton("修改");

    UpdateSRViewHandler updateSRViewHandler;
    public UpdateSRView(MainView mainView, SelectSRPK selectSRPK){
        super(mainView,"修改成绩记录",true);

        updateSRViewHandler=new UpdateSRViewHandler(this,mainView);

        //查询selectedRecordsId对应的记录并回显
        StudentService studentService = new StudentServiceImpl();
        SRDo selectedSR = studentService.getById(selectSRPK);

        SnoLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(SnoLabel);
        SnoTxt.setPreferredSize(new Dimension(200,30));
        SnoTxt.setText(selectedSR.getSno());
        SnoTxt.setEnabled(false);//设置此项不可编辑
        jPanel.add(SnoTxt);

        RnoLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(RnoLabel);
        RnoTxt.setPreferredSize(new Dimension(200,30));
        RnoTxt.setText(selectedSR.getRno());
        RnoTxt.setEnabled(false);//设置此项不可编辑
        jPanel.add(RnoTxt);

        GradeLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(GradeLabel);
        GradeTxt.setPreferredSize(new Dimension(200,30));
        GradeTxt.setText(String.valueOf(selectedSR.getGrade()));
        jPanel.add(GradeTxt);

        UpdateBtn.addActionListener(updateSRViewHandler);
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
    public SRDo bulidUpdatedSRDo() {
        SRDo srDo = new SRDo();
        srDo.setSno(SnoTxt.getText());
        srDo.setRno(RnoTxt.getText());
        srDo.setGrade(Integer.valueOf(GradeTxt.getText()));
        return srDo;
    }
}
