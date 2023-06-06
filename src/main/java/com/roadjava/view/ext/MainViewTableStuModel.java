package com.roadjava.view.ext;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class MainViewTableStuModel extends DefaultTableModel {
    static Vector<String> columns = new Vector<>();
    static{
        columns.addElement("学号");
        columns.addElement("姓名");
        columns.addElement("性别");
        columns.addElement("年龄");
        columns.addElement("班级");
    }
    private MainViewTableStuModel(){
        super(null,columns);
    }
    private static MainViewTableStuModel mainViewTableStuModel = new MainViewTableStuModel();

    public static MainViewTableStuModel assembleModel(Vector<Vector<Object>> data){
        mainViewTableStuModel.setDataVector(data,columns);
        return mainViewTableStuModel;
    }
    public static void updateModel(Vector<Vector<Object>> data){
        mainViewTableStuModel.setDataVector(data,columns);
    }

    public static Vector<String> getColumns(){
        return columns;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
