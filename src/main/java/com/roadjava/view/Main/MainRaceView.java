package com.roadjava.view.Main;

import com.roadjava.handler.Main.MainRaceViewHandler;
import com.roadjava.req.RaceRequest;
import com.roadjava.res.TableDTO;
import com.roadjava.service.interf.RaceService;
import com.roadjava.service.impl.RaceServiceImpl;
import com.roadjava.view.ext.MainViewTable;
import com.roadjava.view.ext.MainViewTableRaceModel;
import com.roadjava.util.DimensionUtil;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class MainRaceView extends JFrame {
    JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JButton addBtn = new JButton("增加");
    JButton updateBtn = new JButton("修改");
    JButton delBtn = new JButton("删除");
    JLabel searchLabel = new JLabel("比赛编号：");
    JTextField searchTxt = new JTextField(15);
    JLabel rnameLabel = new JLabel("比赛名称：");
    JTextField rnameTxt = new JTextField(15);
    JButton searchBtn= new JButton("查询");

    JPanel southPanel = new JPanel(new FlowLayout((FlowLayout.RIGHT)));
    JButton preBtn = new JButton("上一页");
    JButton nextBtn = new JButton("下一页");

    MainViewTable mainViewTable;

    private int pageNow=1;//当前第几页
    private int pagesize=3;//一页显示的记录条数
    MainRaceViewHandler mainRaceViewHandler;

    String tableName;
    public MainRaceView(String tableName) {
        super("运动会信息管理系统-比赛项目表");
        this.tableName=tableName;
        mainViewTable = new MainViewTable(tableName);

        Container contentPane = getContentPane();
        mainRaceViewHandler = new MainRaceViewHandler(this);

        LayoutNorth(contentPane);//背面
        LayoutCenter(contentPane);//中间
        LayoutSouth(contentPane);//南面

        URL imgUrl = MainRaceView.class.getClassLoader().getResource("ice.png");
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

        MainViewTableRaceModel mainViewTableRaceModel = MainViewTableRaceModel.assembleModel(dto.getData());//设置固定的列名
        mainViewTable.setModel(mainViewTableRaceModel);
        mainViewTable.renderRule();//表格列的渲染方式，如每列多宽
        JScrollPane jScrollPane = new JScrollPane(mainViewTable);//放在滚动面板上
        contentPane.add(jScrollPane,BorderLayout.CENTER);
        showPreNext(dto.getTotalCount());
    }

    private void LayoutNorth(Container contentPane) {
        //增加事件监听
        addBtn.addActionListener(mainRaceViewHandler);
        updateBtn.addActionListener(mainRaceViewHandler);
        delBtn.addActionListener(mainRaceViewHandler);
        searchBtn.addActionListener(mainRaceViewHandler);
        northPanel.add(addBtn);
        northPanel.add(updateBtn);
        northPanel.add(delBtn);
        searchLabel.setPreferredSize(new Dimension(70,30));
        northPanel.add(searchLabel);
        northPanel.add(searchTxt);
        rnameLabel.setPreferredSize(new Dimension(70,30));
        northPanel.add(rnameLabel);
        northPanel.add(rnameTxt);
        northPanel.add(searchBtn);

        contentPane.add(northPanel,BorderLayout.NORTH);
    }

    private void LayoutSouth(Container contentPane) {
        preBtn.addActionListener(mainRaceViewHandler);
        nextBtn.addActionListener(mainRaceViewHandler);

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

        MainViewTableRaceModel.updateModel(dto.getData());

        mainViewTable.renderRule();//控制行距等的渲染
        showPreNext(dto.getTotalCount());
    }

    private TableDTO getTableDTO() {
        RaceService raceService = new RaceServiceImpl();
        RaceRequest request = new RaceRequest();
        request.setPageNow(pageNow);
        request.setPageSize(pagesize);
        request.setSerachKey(searchTxt.getText().trim());
        request.setRname(rnameTxt.getText().trim());
        TableDTO tableDTO = raceService.retrieveRaces(request);
        return tableDTO;
    }

    public String[] getSelectedRaces() {
        int[] selectedRows = mainViewTable.getSelectedRows();
        String[] Rnos = new String[selectedRows.length];
        for (int i = 0; i < selectedRows.length; i++) {
            int rowIndex = selectedRows[i];
            Object RnoObj = mainViewTable.getValueAt(rowIndex, 0);
            Rnos[i] = String.valueOf(RnoObj.toString());
        }
        return Rnos;
    }
}
