package utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JavasmDBUtil {
    //Hide constructor
    private static final String propertiesLocation = "src/javasmmarket.properties";

    private JavasmDBUtil() {
    }

    private static Properties properties;
    private static String url;

    private static DataSource dataSource;


    //Get properties
    static {
        properties = new Properties();
        try {
            properties.load(new FileInputStream(propertiesLocation));
            url = properties.getProperty("url");
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static DataSource getDataSource() {
        return dataSource;
    }

    //Get connection
    public static Connection getConnection() {
        try {
//            Connection connection = DriverManager.getConnection(url, properties);
            Connection connection = dataSource.getConnection();//从数据库连接池中获得connection
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Get preparedStatement
    public static PreparedStatement getPreparedStatement(String sql) {
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            return preparedStatement;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Get statement
    public static Statement getStatement() {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            return statement;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //Close all(resultSet,preparedStatement,connection)
    public static void close(ResultSet set, PreparedStatement preparedStatement, Connection connection) {
        if (set != null) {
            try {
                set.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //close all(resultSet,preparedStatement)
    public static void close(ResultSet set, PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                Connection connection = preparedStatement.getConnection();
                close(set, preparedStatement, connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //close all(preparedStatement)
    public static void close(PreparedStatement preparedStatement) {
        close(null, preparedStatement);
    }

    //Close (preparedStatement,connection)
    public static void close(PreparedStatement preparedStatement, Connection connection) {
        close(null, preparedStatement, connection);
    }

    //Close (connection)
    public static void close(Connection connection) {
        close(null, null, connection);
    }

}
