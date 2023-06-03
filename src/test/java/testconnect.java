import java.sql.*;

public class testconnect {
    public static void main(String[] args) throws Exception{
        Class.forName("oracle.jdbc.driver.OracleDriver");//加载驱动
        Connection connection = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:orcl",
                "cjpro",
                "123456");
        System.out.println(connection);

        //创建sql语句
        //String sql="SELECT pwd from manager where user_name= ? ";
        String sql="select pwd from manager where user_name = ? ";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1,"admin");
        ResultSet resultSet = ps.executeQuery();
        while(resultSet.next()){
            String pwd = resultSet.getString(1);
            System.out.println(pwd);
        }

        /*String sql="select pwd from manager where user_name = 'admin' ";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();
        while(resultSet.next()){
            String pwd = resultSet.getString(1);
            System.out.println(pwd);
        }*/




        //获取处理块
        //Statement statement = connection.createStatement();

        //执行sql语句
        //ResultSet resultSet = statement.executeQuery(sql);

        //获取结果
        /*System.out.printf("%-10s %-10s %-10s\n","Sno","Cno","Grade");
        while(resultSet.next()){
            System.out.printf("%-10s %-10s ",resultSet.getString("Sno"),resultSet.getString("Cno"));
            System.out.println(resultSet.getInt("grade"));
        }*/

        resultSet.close();
        //statement.close();
        connection.close();
    }
}
