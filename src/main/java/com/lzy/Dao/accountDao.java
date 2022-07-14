package com.lzy.Dao;

import com.lzy.Model.accounts;
import com.lzy.utils.MyJDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class accountDao {
    private static Connection connection;
    private static Statement statement;    //静态SQL操作
    private PreparedStatement preparedStatement;    //动态SQL操作

    public static List<accounts> accountList() throws SQLException {
        List<accounts> accountList = new ArrayList<>();
        ResultSet resultSet = null;
        String sql = "select * from accountTable";
        //链接数据库
        connection = MyJDBC.getConnection();
        //获取操作数据库的对象Statement
        statement = connection.createStatement();
        //操作数据库
        resultSet = statement.executeQuery(sql);//用于操作DQL语句

        while (resultSet.next()){
            String account = resultSet.getString(1);
            String pwd = resultSet.getString(2);
            String name = resultSet.getString(3);
            String email = resultSet.getString(4);
            accounts accounts = new accounts(account,pwd,name,email);
            accountList.add(accounts);
        }
        return accountList;
    }
    public void addAccount(String sql) throws SQLException{
        connection = MyJDBC.getConnection();
        //获取操作数据库的对象Statement
        statement = connection.createStatement();
        //操作数据库
        statement.executeUpdate(sql);//用于操作DQL语句

    }
}
