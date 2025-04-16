package me.peak.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class DBUtil {

    private String userName;
    private String password;
    private String url;
    private Connection connection;
    private static Logger logger = LoggerFactory.getLogger(DBUtil.class);

    public DBUtil(String url, String userName, String password) {
        this.url = url;
        this.userName = userName;
        this.password = password;
        connect();
    }

    private void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, userName, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void query(String sql) {
        Statement statement = null;
        try {
            logger.info("execute query start {}", sql);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            logger.info("execute resutl is {}", resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void execute(String sql) {
        Statement statement = null;
        try {
            logger.info("execute update start {}" , sql);
            statement = connection.createStatement();
            int res = statement.executeUpdate(sql);
            logger.info("execute update end {}", res);
        } catch (SQLException e) {
            logger.error("update error", e);
        }
    }

    public PreparedStatement prepareState(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }



}
