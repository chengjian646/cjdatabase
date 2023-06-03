package com.roadjava.student.view.ext;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Vector;

public class MainViewTable extends JTable{
    public MainViewTable() {
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
        Vector<String> columns = MainViewTableModel.getColumns();
        MainViewCellRender render = new MainViewCellRender();
        for(int i=0;i<columns.size();i++){
            TableColumn column = getColumn(columns.get(i));
            column.setCellRenderer(render);
            if(i==0){
                column.setPreferredWidth(100);
                column.setMaxWidth(100);
                column.setResizable(false);
            }
        }
    }
}
