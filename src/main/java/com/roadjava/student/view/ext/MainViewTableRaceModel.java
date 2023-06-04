package com.roadjava.student.view.ext;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class MainViewTableRaceModel extends DefaultTableModel {
    static Vector<String> columns = new Vector<>();
    static{
        columns.addElement("比赛编号");
        columns.addElement("比赛名称");
        columns.addElement("先决比赛编号");
    }
    private MainViewTableRaceModel(){
        super(null,columns);
    }
    private static MainViewTableRaceModel mainViewTableModel = new MainViewTableRaceModel();

    public static MainViewTableRaceModel assembleModel(Vector<Vector<Object>> data){
        mainViewTableModel.setDataVector(data,columns);
        return mainViewTableModel;
    }
    public static void updateModel(Vector<Vector<Object>> data){
        mainViewTableModel.setDataVector(data,columns);
    }

    public static Vector<String> getColumns(){
        return columns;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
