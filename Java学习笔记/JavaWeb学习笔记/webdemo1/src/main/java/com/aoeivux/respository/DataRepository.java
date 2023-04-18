package com.aoeivux.respository;

import com.aoeivux.entity.Admin;
import com.aoeivux.util.DataTools;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataRepository {

    public List<Admin> selectAll() {
        String sql = "select * from tb_admin";
        List<Admin> res = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //加载驱动, 获取链接
        connection = DataTools.getConnection();
        // 获取预处理
        try {
            preparedStatement = connection.prepareStatement(sql);
            //获取结果集合
            resultSet = preparedStatement.executeQuery();

            Admin admin = null; // 节省了堆栈内存空间
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String gender = resultSet.getString(3);
                admin = new Admin(id, name, gender);
                res.add(admin);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DataTools.release(connection, preparedStatement, resultSet);
        }
        return res;
    }


    public void add(Admin admin) {
        String sql = "INSERT INTO tb_admin (name, gender) VALUES (?, ?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DataTools.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, admin.getName());
            preparedStatement.setString(2, String.valueOf(admin.getGender()));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DataTools.release(connection, preparedStatement);
        }
    }

    public void delete(Integer id) {
        String sql = "delete from tb_admin where id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        connection = DataTools.getConnection();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DataTools.release(connection, preparedStatement);
        }
    }

    public int update(Integer id, String name, String gender) {
        String sql = "update tb_admin set name=?, gender=? where id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        connection = DataTools.getConnection();
        int i;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,gender);
            preparedStatement.setInt(3, id);
            i = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DataTools.release(connection, preparedStatement);
        }
        return i;
    }



    public static void main(String[] args) {
        DataRepository dataRepository = new DataRepository();
        int update = dataRepository.update(104, "韩l", "saa");
        System.out.println(update);
    }
}
