package com.roadjava.view.Main;

import com.roadjava.entity.SelectSRPK;
import com.roadjava.handler.Main.MainViewHandler;
import com.roadjava.req.StudentRequest;
import com.roadjava.res.TableDTO;
import com.roadjava.service.interf.StudentService;
import com.roadjava.service.impl.StudentServiceImpl;
import com.roadjava.view.ext.MainViewTable;
import com.roadjava.view.ext.MainViewTableModel;
import com.roadjava.util.DimensionUtil;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class MainView extends JFrame {
    JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JButton addBtn = new JButton("增加");
    JButton updateBtn = new JButton("修改");
    JButton delBtn = new JButton("删除");
    JLabel searchLabel = new JLabel("学号：");
    JTextField searchTxt = new JTextField(15);
    JButton searchBtn= new JButton("查询");

    JLabel gradeLabel = new JLabel("成绩查询范围：");
    JTextField gradelow = new JTextField(10);
    JLabel charLabel = new JLabel(" -");
    JTextField gradehigh = new JTextField(10);

    JPanel southPanel = new JPanel(new FlowLayout((FlowLayout.RIGHT)));
    JButton preBtn = new JButton("上一页");
    JButton nextBtn = new JButton("下一页");

    MainViewTable mainViewTable;

    private int pageNow=1;//当前第几页
    private int pagesize=3;//一页显示的记录条数
    MainViewHandler mainViewHandler;

    String tableName;
    public MainView(String tableName) {
        super("运动会信息管理系统-选赛表");
        this.tableName=tableName;
        mainViewTable = new MainViewTable(tableName);

        Container contentPane = getContentPane();
        mainViewHandler = new MainViewHandler(this);

        LayoutNorth(contentPane);//背面
        LayoutCenter(contentPane);//中间
        LayoutSouth(contentPane);//南面

        URL imgUrl = MainView.class.getClassLoader().getResource("ice.png");
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

        MainViewTableModel mainViewTableModel = MainViewTableModel.assembleModel(dto.getData());//设置固定的列名
        mainViewTable.setModel(mainViewTableModel);
        mainViewTable.renderRule();//表格列的渲染方式，如每列多宽
        JScrollPane jScrollPane = new JScrollPane(mainViewTable);//放在滚动面板上
        contentPane.add(jScrollPane,BorderLayout.CENTER);
        showPreNext(dto.getTotalCount());
    }

    private void LayoutNorth(Container contentPane) {
        //增加事件监听
        addBtn.addActionListener(mainViewHandler);
        updateBtn.addActionListener(mainViewHandler);
        delBtn.addActionListener(mainViewHandler);
        searchBtn.addActionListener(mainViewHandler);
        northPanel.add(addBtn);
        northPanel.add(updateBtn);
        northPanel.add(delBtn);
        searchLabel.setPreferredSize(new Dimension(50,30));
        northPanel.add(searchLabel);
        northPanel.add(searchTxt);


        gradeLabel.setPreferredSize(new Dimension(100,30));
        northPanel.add(gradeLabel);
        northPanel.add(gradelow);
        charLabel.setPreferredSize(new Dimension(10,30));
        northPanel.add(charLabel);
        northPanel.add(gradehigh);
        northPanel.add(searchBtn);
        contentPane.add(northPanel,BorderLayout.NORTH);
    }

    private void LayoutSouth(Container contentPane) {
        preBtn.addActionListener(mainViewHandler);
        nextBtn.addActionListener(mainViewHandler);

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

        MainViewTableModel.updateModel(dto.getData());

        mainViewTable.renderRule();//控制行距等的渲染
        showPreNext(dto.getTotalCount());
    }

    private TableDTO getTableDTO() {
        StudentService studentService = new StudentServiceImpl();
        StudentRequest request = new StudentRequest();
        request.setPageNow(pageNow);
        request.setPageSize(pagesize);
        request.setSerachKey(searchTxt.getText().trim());
        request.setGradeLow(gradelow.getText().trim());
        request.setGradeHigh(gradehigh.getText().trim());
        TableDTO tableDTO = studentService.retrieveStudents(request);
        return tableDTO;
    }

    //StuRace表有两个主码，封装成对象
    public  SelectSRPK getSelectedRecordsIds(){
        int[] selectedRows = mainViewTable.getSelectedRows();
        SelectSRPK selectSRPK = new SelectSRPK(selectedRows.length);
        String[] Sno = new String[selectedRows.length];
        String[] Rno = new String[selectedRows.length];
        for (int i = 0; i < selectedRows.length; i++) {
            int rowIndex = selectedRows[i];
            Object idObjSno = mainViewTable.getValueAt(rowIndex, 0);
            Object idObjRno = mainViewTable.getValueAt(rowIndex, 1);
            Sno[i]=idObjSno.toString();
            Rno[i]=idObjRno.toString();
        }
        selectSRPK.setSno(Sno);
        selectSRPK.setRno(Rno);
        return selectSRPK;
    }

}
