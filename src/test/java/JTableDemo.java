import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.Vector;

public class JTableDemo extends JFrame{
    public JTableDemo(){
        super("测试jtable");
        Vector<String> columns = new Vector<>();
        columns.addElement("编号");
        columns.addElement("姓名");
        columns.addElement("学号");
        columns.addElement("家乡");
        columns.addElement("语文");
        columns.addElement("数学");
        columns.addElement("英语");
        columns.addElement("总分");

        Vector<Vector<Object>> data = new Vector<>();

        Vector<Object> rowVector1 = new Vector<>();
        rowVector1.addElement("1");
        rowVector1.addElement("张三");
        rowVector1.addElement("no1");
        rowVector1.addElement("安徽");
        rowVector1.addElement("1");
        rowVector1.addElement("2");
        rowVector1.addElement("3");
        rowVector1.addElement("6");

        Vector<Object> rowVector2 = new Vector<>();
        rowVector2.addElement("2");
        rowVector2.addElement("李四");
        rowVector2.addElement("no2");
        rowVector2.addElement("安徽");
        rowVector2.addElement("2");
        rowVector2.addElement("2");
        rowVector2.addElement("3");
        rowVector2.addElement("7");

        Vector<Object> rowVector3 = new Vector<>();
        rowVector3.addElement("3");
        rowVector3.addElement("王五");
        rowVector3.addElement("no3");
        rowVector3.addElement("安徽");
        rowVector3.addElement("3");
        rowVector3.addElement("3");
        rowVector3.addElement("3");
        rowVector3.addElement("9");

        data.addElement(rowVector1);
        data.addElement(rowVector2);
        data.addElement(rowVector3);
        
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.setDataVector(data,columns);
        //jTabel和table关联
        JTable jTable = new JTable(tableModel);
        //设置表头
        JTableHeader tableHeader = jTable.getTableHeader();
        tableHeader.setFont(new Font(null,Font.BOLD,16));
        tableHeader.setForeground(Color.red);
        //设置表格体
        jTable.setFont(new Font(null,Font.PLAIN,14));
        jTable.setForeground(Color.black);//字体颜色
        jTable.setGridColor(Color.BLACK);//线的颜色
        jTable.setRowHeight(30);
        jTable.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        Container contentPane = getContentPane();

        JScrollPane jScrollPane = new JScrollPane(jTable);//jTable放在滚动面板上
        contentPane.add(jScrollPane);

        setSize(600,400);
        //设置居中显示
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        int offsetX = (screenSize.width - 600) / 2;
        int offsetY = (screenSize.height - 400) / 2;
        setLocation(offsetX,offsetY);
        //关闭时退出程序
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //窗口大小不可改变
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new JTableDemo();
    }
}
