package com.javasm.base;

import com.javasm.annotation.Column;
import com.javasm.annotation.ID;
import com.javasm.annotation.Table;
import com.javasm.utils.DBHelper;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

//所有 其他的DAO 继承我
// 继承我的DAO 会拥有我的方法
//有了规则，想使用 selectObjectById 方法 泛型 必须 加@Table注解和主键加@ID注解
public class BaseDAO<T> extends Base<T>{

    //TODO:查询全部 不带分页,有待完善
    //如果传入主键，更新，没有主键，添加
    public void saveOrUpdate(T t) {
        //找到主键
        Class clazz = getTClass();
        Field[] fields = clazz.getDeclaredFields();
        Object id = null;
        String idName = null;
        for (Field field : fields) {
            ID idAnnotation = field.getAnnotation(ID.class);
            if (idAnnotation != null) {//主键
                //调用getter方法 获取主键的值
                id = getterMethod(t, field.getName());
                idName = idAnnotation.value();
                break;
            }
        }
        //添加
        StringBuilder sql = new StringBuilder();
        StringBuilder endsql = new StringBuilder();
        List<Object> paramList = new ArrayList<>();
        if (id == null){
            //INSERT INTO goods ( name,price) VALUES (?,?)
            sql.append("INSERT INTO ");
            sql.append(getTableName());
            sql.append(" ( ");
            int i = 0;
            for (Field field : fields){
                ID idAnnotation = field.getAnnotation(ID.class);
                if (idAnnotation == null){//不要id
                    Column columnAnnotation = field.getAnnotation(Column.class);
                    if (columnAnnotation!=null){
                        String columnName = columnAnnotation.value();
                        if (i!=0){
                            sql.append(",");
                            endsql.append(",");
                        }
                        sql.append(columnName);
                        endsql.append("?");
                        i++;
                        paramList.add(getterMethod(t, field.getName()));
                    }
                }
            }
            sql.append(" ) VALUES (");
            sql.append(endsql);
            sql.append(" )");
            try {
                DBHelper.getQueryRunner().update(sql.toString(),paramList.toArray(new Object[0]));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else {
            //修改
            //UPDATE goods SET goods_name='测试' WHERE id = 11;
            sql.append("UPDATE ");
            sql.append(getTableName());
            sql.append(" SET ");
            int i = 0;
            for (Field field : fields){
                ID idAnnotation = field.getAnnotation(ID.class);
                if (idAnnotation == null){//不要id
                    Column columnAnnotation = field.getAnnotation(Column.class);
                    if (columnAnnotation!=null){
                        String columnName = columnAnnotation.value();
                        if (i!=0){
                            sql.append(",");
                        }
                        sql.append(columnName);
                        sql.append("=");
                        sql.append("?");
                        i++;
                        paramList.add(getterMethod(t, field.getName()));
                    }
                }
            }
            sql.append(" WHERE ");
            sql.append(idName);
            sql.append("= ?");
            paramList.add(id);
            try {
                DBHelper.getQueryRunner().update(sql.toString(),paramList.toArray(paramList.toArray(new Object[0])));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //根据id删除
    public void deleteById(Integer id){
        //只能根据主键删除
        String sql = "DELETE FROM "+getTableName()+" WHERE "+getIdName()+" = ?";
        try {
            DBHelper.getQueryRunner().update(sql,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //查询单条
    public T selectObject(String sql, Object... parameters) {
        try {
            return DBHelper.getQueryRunner().query(sql,
                    new BeanHandler<>(getTClass(), new BasicRowProcessor(new GenerousBeanProcessor())),
                    parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //查询集合
    public List<T> selectList(String sql, Object... parameters) {
        BeanListHandler<T> beanListHandler = new BeanListHandler<>(getTClass(), new BasicRowProcessor(new GenerousBeanProcessor()));
        try {
            return DBHelper.getQueryRunner().query(sql, beanListHandler, parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //查询 单条 记录  根据id查询
    public T selectObjectById(Integer id) {
        String sql = "SELECT * FROM " + getTableName() + " WHERE " + getIdName() + " = ?";
        return selectObject(sql, id);
    }

    //分页查询
    public List<T> selectListByPage() {
        return selectListByPage(null, null);
    }

    //分页查询不带where条件
    public List<T> selectListByPage(Integer start, Integer size) {
        if (start == null) {
            start = 0;
        }
        if (size == null || size > 1000) {
            size = 10;
        }
        String sql = "SELECT * FROM " + getTableName() + " LIMIT ?,?";
        return selectList(sql, start, size);
    }

    //获取表的名字
    public String getTableName() {
        //获取类的 注解
        Table annotation = getTClass().getAnnotation(Table.class);
        if (annotation != null) {
            String tableName = annotation.value();
            return tableName;
        } else {
            return null;
        }
    }

    //获取主键的名字
    //如果 主键的名字 是id 不需要 使用注解 如果不是id  标记上
    public String getIdName() {
        String idstr = "id";
        //获取 类中 所有的 属性
        //判断 哪个属性上 有@IDJavasm
        Field[] fields = getTClass().getDeclaredFields();
        for (Field f : fields) {
            ID annotation = f.getAnnotation(ID.class);
            if (annotation != null) {
                //找到带 IDJavasm注解的属性
                idstr = annotation.value();
                break;
            }
        }
        return idstr;
    }

    //调用 t对象 filedName属性的 getter方法
    public Object getterMethod(T t, String filedName) {
        String methodName = "get" +
                filedName.substring(0,1).toUpperCase()+
                filedName.substring(1);
        try {
            Method method = getTClass().getMethod(methodName);
            return method.invoke(t);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

}
