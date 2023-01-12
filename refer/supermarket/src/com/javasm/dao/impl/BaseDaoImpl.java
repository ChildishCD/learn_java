package com.javasm.dao.impl;

import com.javasm.dao.BaseDao;
import com.javasm.util.DBUtil;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: Lisa
 * @className: BaseDaoImpl
 * @description:
 * @date: 2021/6/15 11:26
 * @version: 0.1
 * @since: 1.8
 */
public class BaseDaoImpl<T> implements BaseDao<T> {

    //获得T 的Class
    private Class<T> tClass;
    private String tableName;

    public BaseDaoImpl(Class<T> tClass) {
        this.tClass = tClass;
        this.tableName = tClass.getSimpleName();//类名
    }

    //映射
    @Override
    public T selectOne(int id) throws Exception {
        String sql = "select * from " + tableName + " where id =?";
        return new QueryRunner(DBUtil.getDataSource()).query(sql, new BeanHandler<>(tClass, new BasicRowProcessor(new GenerousBeanProcessor())), id);
    }

    @Override
    public List<T> selectAll() throws Exception {
        String sql = "select * from " + tableName;
        return new QueryRunner(DBUtil.getDataSource()).query(sql, new BeanListHandler<>(tClass, new BasicRowProcessor(new GenerousBeanProcessor())));
    }

    @Override
    public List<T> selectByPrams(Map<String, Object> params) throws Exception {
        //等值操作
        StringBuilder builder = new StringBuilder(" select * from ");
        builder.append(tableName).append(" where  ");
        Set<Map.Entry<String, Object>> set = params.entrySet();
        for (Map.Entry<String, Object> entry : set) {
            String columnName = entry.getKey();
            Object columnValue = entry.getValue();
            builder.append(columnName);
            builder.append("='");
            builder.append(columnValue);
            builder.append("'");
            builder.append(" and ");
        }

        builder.delete(builder.lastIndexOf("and"), builder.length());
        return new QueryRunner(DBUtil.getDataSource()).query(builder.toString(), new BeanListHandler<>(tClass, new BasicRowProcessor(new GenerousBeanProcessor())));
    }

    @Override
    public int deleteOne(int id) throws Exception {
        String sql = "delete from " + tableName + " where id =?";
        return new QueryRunner(DBUtil.getDataSource()).update(sql, id);
    }
}
