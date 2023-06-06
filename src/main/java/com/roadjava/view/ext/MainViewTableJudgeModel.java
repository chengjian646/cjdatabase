package com.roadjava.view.ext;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class MainViewTableJudgeModel extends DefaultTableModel {
    static Vector<String> columns = new Vector<>();
    static{
        columns.addElement("工号");
        columns.addElement("姓名");
    }
    private MainViewTableJudgeModel(){
        super(null,columns);
    }
    private static MainViewTableJudgeModel mainViewTableJudgeModel = new MainViewTableJudgeModel();

    public static MainViewTableJudgeModel assembleModel(Vector<Vector<Object>> data){
        mainViewTableJudgeModel.setDataVector(data,columns);
        return mainViewTableJudgeModel;
    }
    public static void updateModel(Vector<Vector<Object>> data){
        mainViewTableJudgeModel.setDataVector(data,columns);
    }

    public static Vector<String> getColumns(){
        return columns;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
