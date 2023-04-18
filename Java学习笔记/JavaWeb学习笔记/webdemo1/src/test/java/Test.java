import com.aoeivux.entity.Admin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Test {
    private static String url = "jdbc:mysql://localhost:3306/zhxy_db?useUnicode=true&";
    private static String userName = "root";
    private static String password = "Atheonealone37.";

    public static void main(String[] args){
        String sql = "select * from zhxy_db";
        List<Admin> res = new ArrayList<>();
        try {
            //加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //获取链接
            Connection connection = DriverManager.getConnection(url, userName, password);
            // 获取预处理
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
