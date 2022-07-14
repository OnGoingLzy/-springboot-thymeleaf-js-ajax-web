package com.lzy.utils;

import java.sql.*;

public class MyJDBC {

    /**
     * JDBC操作数据库的步骤：
     *  1、引入取得jar包
     *  2、加载驱动
     *  3、获取Connection链接数据库的对象
     *  4、获取Statement操作数据库的对象
     *      如果操作的是DQL语句会返回一个结果集
     *      我们需要处理此结果集
     *      如果操作的是DML语句，会返回一个int类型的值，表示操作成功的行数，如果是0则表示没有操作成功
     *  5、【提交事务】关闭数据库，释放资源
     * */

    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/test";
    private static String user = "root";
    private static String password = "lzy3122588";


    //加载驱动
    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //获取操作数据库的对象Connection
    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        connection = DriverManager.getConnection(url,user,password);
        System.out.println("数据库连接成功");
        return connection;
    }

    public static void close(Statement statement, Connection connection) {
        close(null,statement,connection);
    }


    //关闭数据库的方法
    public static void close(ResultSet resultSet, Statement statement, Connection connection){
        if (resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}