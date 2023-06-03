import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class JFrameTest extends JFrame{
    JButton jButton;
    public JFrameTest(){
        super("This is cjtitle");

        jButton = new JButton("This is a Button");
        Container contentPane=getContentPane();
        contentPane.add(jButton);
        //设置窗体图标
        URL resource = JFrameTest.class.getClassLoader().getResource("ice.png");
        Image image = new ImageIcon(resource).getImage();
        setIconImage(image);

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
        new JFrameTest();
    }
}
