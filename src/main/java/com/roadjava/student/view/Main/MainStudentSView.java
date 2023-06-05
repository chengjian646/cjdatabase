package com.roadjava.student.view.Main;

import com.roadjava.handler.Main.MainStudentSViewHandler;
import com.roadjava.req.StudentSRequest;
import com.roadjava.res.TableDTO;
import com.roadjava.service.StudentSService;
import com.roadjava.service.impl.StudentSServiceImpl;
import com.roadjava.student.view.ext.MainViewTable;
import com.roadjava.student.view.ext.MainViewTableStuModel;
import com.roadjava.util.DimensionUtil;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class MainStudentSView extends JFrame {
    JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JButton addBtn = new JButton("增加");
    JButton updateBtn = new JButton("修改");
    JButton delBtn = new JButton("删除");
    JLabel searchLabel = new JLabel("学号：");
    JTextField searchTxt = new JTextField(10);
    JLabel snameLabel = new JLabel("学生姓名：");
    JTextField snameTxt = new JTextField(15);
    JLabel sgenderLabel = new JLabel("性别(M/W)：");
    JTextField sgenderTxt = new JTextField(5);
    
    JLabel ageLabel = new JLabel("年龄查询范围：");
    JTextField agelow = new JTextField(10);
    JLabel charLabel = new JLabel(" -");
    JTextField agehigh = new JTextField(10);
    
    JLabel sclassLabel = new JLabel("班级：");
    JTextField sclassTxt = new JTextField(15);
    JButton searchBtn= new JButton("查询");

    JPanel southPanel = new JPanel(new FlowLayout((FlowLayout.RIGHT)));
    JButton preBtn = new JButton("上一页");
    JButton nextBtn = new JButton("下一页");

    MainViewTable mainViewTable;

    private int pageNow=1;//当前第几页
    private int pagesize=3;//一页显示的记录条数
    MainStudentSViewHandler mainStudentSViewHandler;

    String tableName;
    public MainStudentSView(String tableName) {
        super("运动会信息管理系统-学生信息表");
        this.tableName=tableName;
        mainViewTable = new MainViewTable(tableName);

        Container contentPane = getContentPane();
        mainStudentSViewHandler = new MainStudentSViewHandler(this);

        LayoutNorth(contentPane);//北面
        LayoutCenter(contentPane);//中间
        LayoutSouth(contentPane);//南面

        URL imgUrl = MainStudentSView.class.getClassLoader().getResource("ice.png");
        setIconImage(new ImageIcon(imgUrl).getImage());

        setBounds(DimensionUtil.getBounds());
        //设置窗体完全充满整个屏幕的可见大小
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        //设置居中显示
        setLocationRelativeTo(null);
        //关闭时退出程序
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //窗口大小可改变
        setResizable(true);
        setVisible(true);
    }

    private void LayoutCenter(Container contentPane) {
        TableDTO dto = getTableDTO();

        MainViewTableStuModel mainViewTableStuModel = MainViewTableStuModel.assembleModel(dto.getData());//设置固定的列名
        mainViewTable.setModel(mainViewTableStuModel);
        mainViewTable.renderRule();//表格列的渲染方式，如每列多宽
        JScrollPane jScrollPane = new JScrollPane(mainViewTable);//放在滚动面板上
        contentPane.add(jScrollPane,BorderLayout.CENTER);
        showPreNext(dto.getTotalCount());
    }

    private void LayoutNorth(Container contentPane) {
        //增加事件监听
        addBtn.addActionListener(mainStudentSViewHandler);
        updateBtn.addActionListener(mainStudentSViewHandler);
        delBtn.addActionListener(mainStudentSViewHandler);
        searchBtn.addActionListener(mainStudentSViewHandler);
        northPanel.add(addBtn);
        northPanel.add(updateBtn);
        northPanel.add(delBtn);
        searchLabel.setPreferredSize(new Dimension(50,30));
        northPanel.add(searchLabel);
        northPanel.add(searchTxt);
        snameLabel.setPreferredSize(new Dimension(70,30));
        northPanel.add(snameLabel);
        northPanel.add(snameTxt);
        sgenderLabel.setPreferredSize(new Dimension(50,30));
        northPanel.add(sgenderLabel);
        northPanel.add(sgenderTxt);
        ageLabel.setPreferredSize(new Dimension(100,30));
        northPanel.add(ageLabel);
        northPanel.add(agelow);
        charLabel.setPreferredSize(new Dimension(10,30));
        northPanel.add(charLabel);
        northPanel.add(agehigh);
        
        sclassLabel.setPreferredSize(new Dimension(50,30));
        northPanel.add(sclassLabel);
        northPanel.add(sclassTxt);
        
        northPanel.add(searchBtn);

        contentPane.add(northPanel,BorderLayout.NORTH);
    }

    private void LayoutSouth(Container contentPane) {
        preBtn.addActionListener(mainStudentSViewHandler);
        nextBtn.addActionListener(mainStudentSViewHandler);

        southPanel.add(preBtn);
        southPanel.add(nextBtn);
        contentPane.add(southPanel,BorderLayout.SOUTH);
    }
    private void showPreNext(int totalCount){
        if(pageNow==1){
            preBtn.setVisible(false);
        }else{
            preBtn.setVisible(true);
        }

        int pageCount =0;//总页数
        if(totalCount%pagesize==0){
            pageCount=totalCount/pagesize;
        }else{
            pageCount=totalCount/pagesize+1;
        }
        if(pageNow==pageCount){
            nextBtn.setVisible(false);
        }else {
            nextBtn.setVisible(true);
        }
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getPageNow() {
        return pageNow;
    }

    public void reloadTable() {
        TableDTO dto = getTableDTO();

        MainViewTableStuModel.updateModel(dto.getData());

        mainViewTable.renderRule();//控制行距等的渲染
        showPreNext(dto.getTotalCount());
    }

    private TableDTO getTableDTO() {
        StudentSService studentSService = new StudentSServiceImpl();
        StudentSRequest request = new StudentSRequest();
        request.setPageNow(pageNow);
        request.setPageSize(pagesize);
        request.setSerachKey(searchTxt.getText().trim());
        request.setSname(snameTxt.getText().trim());
        request.setSgender(sgenderTxt.getText().trim());
        request.setSagelow(agelow.getText().trim());
        request.setSagehigh(agehigh.getText().trim());
        request.setSclass(sclassTxt.getText().trim());
        TableDTO tableDTO = studentSService.retrieveStudents(request);
        return tableDTO;
    }

    public String[] getSelectedStu(){
        int[] selectedRows = mainViewTable.getSelectedRows();
        String[] Snos = new String[selectedRows.length];
        for (int i = 0; i < selectedRows.length; i++) {
            int rowIndex = selectedRows[i];
            Object SnoObj = mainViewTable.getValueAt(rowIndex, 0);
            Snos[i]=String.valueOf(SnoObj.toString());
        }
        return Snos;
    }

}
