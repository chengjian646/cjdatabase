package com.roadjava.view.Main;

import com.roadjava.handler.Main.MainJudgeViewHandler;
import com.roadjava.req.JudgeRequest;
import com.roadjava.res.TableDTO;
import com.roadjava.service.interf.JudgeService;
import com.roadjava.service.impl.JudgeServiceImpl;
import com.roadjava.view.ext.MainViewTable;
import com.roadjava.view.ext.MainViewTableJudgeModel;
import com.roadjava.util.DimensionUtil;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class MainJudgeView extends JFrame {
    JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JButton addBtn = new JButton("增加");
    JButton updateBtn = new JButton("修改");
    JButton delBtn = new JButton("删除");
    JLabel searchLabel = new JLabel("裁判工号：");
    JTextField searchTxt = new JTextField(15);
    JLabel jnameLabel = new JLabel("裁判姓名：");
    JTextField jnameTxt = new JTextField(15);
    JButton searchBtn= new JButton("查询");

    JPanel southPanel = new JPanel(new FlowLayout((FlowLayout.RIGHT)));
    JButton preBtn = new JButton("上一页");
    JButton nextBtn = new JButton("下一页");

    MainViewTable mainViewTable;

    private int pageNow=1;//当前第几页
    private int pagesize=3;//一页显示的记录条数
    MainJudgeViewHandler mainJudgeViewHandler;

    String tableName;
    public MainJudgeView(String tableName) {
        super("运动会信息管理系统-裁判信息表");
        this.tableName=tableName;
        mainViewTable = new MainViewTable(tableName);

        Container contentPane = getContentPane();
        mainJudgeViewHandler = new MainJudgeViewHandler(this);

        LayoutNorth(contentPane);//北面
        LayoutCenter(contentPane);//中间
        LayoutSouth(contentPane);//南面

        URL imgUrl = MainJudgeView.class.getClassLoader().getResource("ice.png");
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
        MainViewTableJudgeModel mainViewTableJudgeModel = MainViewTableJudgeModel.assembleModel(dto.getData());//设置固定的列名
        mainViewTable.setModel(mainViewTableJudgeModel);
        mainViewTable.renderRule();//表格列的渲染方式，如每列多宽
        JScrollPane jScrollPane = new JScrollPane(mainViewTable);//放在滚动面板上
        contentPane.add(jScrollPane,BorderLayout.CENTER);
        showPreNext(dto.getTotalCount());
    }

    private void LayoutNorth(Container contentPane) {
        //增加事件监听
        addBtn.addActionListener(mainJudgeViewHandler);
        updateBtn.addActionListener(mainJudgeViewHandler);
        delBtn.addActionListener(mainJudgeViewHandler);
        searchBtn.addActionListener(mainJudgeViewHandler);
        northPanel.add(addBtn);
        northPanel.add(updateBtn);
        northPanel.add(delBtn);
        searchLabel.setPreferredSize(new Dimension(70,30));
        northPanel.add(searchLabel);
        northPanel.add(searchTxt);
        jnameLabel.setPreferredSize(new Dimension(70,30));
        northPanel.add(jnameLabel);
        northPanel.add(jnameTxt);
        northPanel.add(searchBtn);

        contentPane.add(northPanel,BorderLayout.NORTH);
    }

    private void LayoutSouth(Container contentPane) {
        preBtn.addActionListener(mainJudgeViewHandler);
        nextBtn.addActionListener(mainJudgeViewHandler);

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

        MainViewTableJudgeModel.updateModel(dto.getData());

        mainViewTable.renderRule();//控制行距等的渲染
        showPreNext(dto.getTotalCount());
    }

    private TableDTO getTableDTO() {
        JudgeService judgeService = new JudgeServiceImpl();
        JudgeRequest request = new JudgeRequest();
        request.setPageNow(pageNow);
        request.setPageSize(pagesize);
        request.setSerachKey(searchTxt.getText().trim());
        request.setJname(jnameTxt.getText().trim());
        TableDTO tableDTO = judgeService.retrieveStudents(request);
        return tableDTO;
    }

    //StuRace表有两个主码，封装成对象
    public  String[] getSelectedJudge(){
        int[] selectedRows = mainViewTable.getSelectedRows();
        String[] Jnos = new String[selectedRows.length];
        for (int i = 0; i < selectedRows.length; i++) {
            int rowIndex = selectedRows[i];
            Object RnoObj = mainViewTable.getValueAt(rowIndex, 0);
            Jnos[i] = String.valueOf(RnoObj.toString());
        }
        return Jnos;
    }

}
