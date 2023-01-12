package jdbc.demo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import utils.*;

//专门用来查询数据库的类
//不能有业务代码
public class SmFruitDAO {
    //添加水果信息
    //拼接字符串的方法
    //用batch更好
    public Integer insertFruit(SmFruit fruit) {
        StringBuilder sb = new StringBuilder();
        StringBuilder vsb = new StringBuilder();
        //拼接字符串
        {
            sb.append("Insert INTO sm_fruit (name,price");
            vsb.append("VALUES (?,?");
            if (fruit.getInventory() != null) {
                //重新拼接SQL语句
                sb.append(",inventory");
                vsb.append(",?");
            }
            sb.append(",create_time");
            if (fruit.getCreateTime() != null) {
                vsb.append(",?");
            } else {
                vsb.append(",now()");
            }
            //结尾的)是固定要加的
            sb.append(")");
            vsb.append(")");
        }
        String sql = sb.append(vsb).toString();
        //连接数据库
        PreparedStatement preparedStatement = JavasmDBUtil.getPreparedStatement(sql);
        try {
            preparedStatement.setString(1, fruit.getName());
            preparedStatement.setDouble(2, fruit.getPrice());
            if (fruit.getInventory() != null) preparedStatement.setInt(3, fruit.getInventory());

            if (fruit.getCreateTime() != null) {
                //利用char存时间
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String format = simpleDateFormat.format(fruit.getCreateTime());
                preparedStatement.setString(4, format);
            }
//            else if (fruit.getCreateTime() != null) {
                //java.util.Date date = fruit.getCreateTime();
                //LocalDateTime localDateTime = LocalDateTime.now();
                //String str = "2023-01-05 11:15:30";
                // preparedStatement.setTimestamp(3,fruit.getCreateTime());
                //向数据库 set时间格式数据的时候，不能直接传 java uitl下的时间类，要new出来对应的sql时间类
                //preparedStatement.setDate(3,new java.sql.Date(date.getTime()));
                //preparedStatement.setTime(3,new Time(date.getTime()));
//                preparedStatement.setTimestamp(3, new Timestamp(fruit.getCreateTime().getTime()));
                //preparedStatement.setString(3,str);
//            }
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}