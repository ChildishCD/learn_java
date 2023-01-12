package jdbc.book.base;

import utils.JavasmDBUtil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//所有其他的DAO会继承BaseDAO
//在model 或 bean 中的类需要在类名和属性名上添加annotation
public class BaseDAO<T> {
    //知道服务那一张表
    public Class<T> getClassT() {
        //获取泛型 T 的 Class 对象
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    //获取表的基本信息
    public String getTableName() {
        //获取继承了BaseDAO后xxxDAO对应数据库中的表名
        JavasmTable annotation = getClassT().getAnnotation(JavasmTable.class);
        if (annotation != null) {
            return annotation.value();
        }
        return null;
    }

    public String getIdName() {
        //获取对应表中主键的列名
        Field[] declaredFields = getClassT().getDeclaredFields();
        for (Field f : declaredFields) {
            JavasmId annotation = f.getAnnotation(JavasmId.class);
            if (annotation != null) {
                return annotation.value();
            }
        }
        return null;
    }

    //具体的基本DAO操作
    public T selctRowById(Integer id) {
        T row = null;
        String sql = "select * from " + getTableName() + " where " + getIdName() + " = ?";
        PreparedStatement ps = JavasmDBUtil.getPreparedStatement(sql);
        ResultSet rs = null;
        try {
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                row = getClassT().getConstructor(ResultSet.class).newInstance(rs);
            }
        } catch (SQLException | InvocationTargetException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        } finally {
            JavasmDBUtil.close(rs, ps);
        }
        return row;
    }
    //......
}
