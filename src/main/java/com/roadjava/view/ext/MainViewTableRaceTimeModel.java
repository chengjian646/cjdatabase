package com.roadjava.view.ext;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class MainViewTableRaceTimeModel extends DefaultTableModel {
    static Vector<String> columns = new Vector<>();
    static{
        columns.addElement("比赛编号");
        columns.addElement("比赛时间");
        columns.addElement("比赛地点");
        columns.addElement("裁判编号");
    }
    private MainViewTableRaceTimeModel(){
        super(null,columns);
    }
    private static MainViewTableRaceTimeModel mainViewTableRaceTimeModel = new MainViewTableRaceTimeModel();

    public static MainViewTableRaceTimeModel assembleModel(Vector<Vector<Object>> data){
        mainViewTableRaceTimeModel.setDataVector(data,columns);
        return mainViewTableRaceTimeModel;
    }
    public static void updateModel(Vector<Vector<Object>> data){
        mainViewTableRaceTimeModel.setDataVector(data,columns);
    }

    public static Vector<String> getColumns(){
        return columns;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
