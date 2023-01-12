package jdbc.demo;

import utils.JavasmDBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//非正常操作流程, 测试使用
public class Demo {
    //JDBC API creates a bridge between JAVA and Database
    // Import jar package
    // DriverManager: only common database, other kind of database(like GeoDataBase need other support)
    // Connection:  1.Create connection to a given database(local or online)
    // Statement:   2.Send the sql to the database
    //              -.Database get the sql, execute it, and return a message
    // ResultSet:   3.JAVA programme get the message
    // Close:       4.!!!!!CLOSE CONNECTION!!!!!
    public static void main(String[] args) {
//        test1();

//        List<Student> students = getStudentList();
//        System.out.println(Arrays.toString(insertStudent(students)));

//        System.out.println(getExistStudentByNameLike("k"));
        System.out.println(getExistStudent());
    }

    //写一个工具类，仅实现数据库的连接操作
    public static List<Student> getExistStudent() {
        String sql = "select * from student;";
        PreparedStatement preparedStatement = JavasmDBUtil.getPreparedStatement(sql);
        ResultSet set = null;
        List<Student> students = new ArrayList<>();
        try {
            set = preparedStatement.executeQuery();
            while (set.next()){
                students.add(new Student(set.getString("name"), set.getInt("age"), set.getDouble("score")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JavasmDBUtil.close(set,preparedStatement);
        }
        return students;
    }

    public static List<Student> getExistStudentByNameLike(String name) {
        //Connection 会自动扫描包,确保driver就绪
        List<Student> students = null;
        name = "%" + name + "%";
        String sql = "select * from student where name like ?";
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/javasm", "root", "root");
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setString(1, name);
            ResultSet resultSet = ps.executeQuery();
            students = new ArrayList<>();
            while (resultSet.next()) {
                students.add(new Student(resultSet.getString("name"), resultSet.getInt("age"), resultSet.getDouble("score")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    public static int[] insertStudent(List<Student> students) {
        //PreparedStatement 预编译,性能好
        //Statement 直译 有拼接字符串的风险
        //避免sql注入
        //  如果直接传入sql语句的字符串,可能会根据输入的信息自己写出sql语句, 同样能够执行
        //不能处理Like语句
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Connection connection = null;
        PreparedStatement ps = null;
        int[] i = null;
        try {
            String url = "jdbc:mysql://127.0.0.1:3306/javasm";
            String username = "root";
            String password = "root";
            connection = DriverManager.getConnection(url, username, password);

            String sql = "insert into student (name,age,score,create_time) values (?,?,?,NOW())";
            ps = connection.prepareStatement(sql);
            //------- 判断传入的list null/isEmpty ------------
            for (Student s : students) {
                ps.setString(1, s.getName());
                ps.setInt(2, s.getAge());
                ps.setDouble(3, s.getScore());
//                ps.executeUpdate();
                ps.addBatch();
            }
            i = ps.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    public static List<Student> getStudentList() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Tom", 30, 61.0));
        students.add(new Student("Ben", 25, 55.0));
        return students;
    }

    private static void test1() {
        //-1. 加载jar包
        //0.加载驱动
        //加载驱动,告诉JDBC所连接数据库的类型(固定程序)
        //  新版本驱动: com.mysql.cj.jdbc.Driver
        //  旧版本驱动: com.mysql.jdbc.Driver
        //  地址在 包-->META-INF-->services-->java.sql.Driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        Connection connection = null;
        Statement statement = null;
        try {
            //1.连接数据库
            //  利用DriverManager.getConnection(url,name,pass)创建Connection对象
            //  url: "jdbc:数据库类型名://数据库所在ip:对应端口(/数据库名)"
            //      数据库名如果省略,每条查询语句的表名前面都需要加上数据库名称
            String url = "jdbc:mysql://127.0.0.1:3306/javasm";
            String username = "root";
            String password = "root";
            connection = DriverManager.getConnection(url, username, password);
            System.out.println(connection);
            //2.传输sql语句
            //  利用Connection.createStatement()创建Statement对象
            //  利用Statement.executeUpdate(sql)执行语句
            statement = connection.createStatement();
            String sql1 = "insert into student (name,age,score) values ('Ben',54,99)";
            String sql2 = "delete from student where age > 50";
            //sql 注入
            //String sql3 = "insert into student (name,age,score) values ('" + name + "',54,99)"
            int i = statement.executeUpdate(sql2);

            //3.获得结果
            System.out.println(i);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //4.关闭连接
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
