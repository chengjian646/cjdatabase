package com.roadjava.student.view.ext;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Vector;

public class MainViewTable extends JTable{
    String tableName;
    public MainViewTable(String tableName) {
        this.tableName=tableName;
        
        JTableHeader tableHeader = getTableHeader();
        tableHeader.setFont(new Font(null, Font.BOLD, 16));
        tableHeader.setForeground(Color.red);
        //设置表格体
        setFont(new Font(null, Font.PLAIN, 14));
        setForeground(Color.black);//字体颜色
        setGridColor(Color.BLACK);//线的颜色
        setRowHeight(30);
        getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);//可选中多行
    }
    public void renderRule(){
        //设置表格列的渲染方式
        Vector<String> columns = null;
        if("选赛表".equals(tableName)){
            columns = MainViewTableModel.getColumns(); 
        }else if("比赛项目表".equals(tableName)){
            columns = MainViewTableRaceModel.getColumns();
        }else if("赛程表".equals(tableName)){
            columns = MainViewTableRaceTimeModel.getColumns();
        }else if("学生信息表".equals(tableName)){
            columns = MainViewTableStuModel.getColumns();
        }else if("裁判信息表".equals(tableName)){
            columns = MainViewTableJudgeModel.getColumns();
        }

        MainViewCellRender render = new MainViewCellRender();
        for(int i=0;i<columns.size();i++){
            TableColumn column = getColumn(columns.get(i));
            column.setCellRenderer(render);//设置隔行变色

            if(i==0){//设置第一列固定长度
                column.setPreferredWidth(100);
                column.setMaxWidth(100);
                column.setResizable(false);
            }
        }
    }
}
