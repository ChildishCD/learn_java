package com.javasm.dao.impl;

import com.javasm.bean.Type;
import com.javasm.dao.TypeDao;
import com.javasm.sql.TypeSql;
import com.javasm.util.DBUtil;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.util.List;

/**
 * @author: Lisa
 * @className: TypeDaoImpl
 * @description:
 * @date: 2021/6/15 10:17
 * @version: 0.1
 * @since: 1.8
 */
public class TypeDaoImpl extends BaseDaoImpl<Type> implements TypeDao {

    private Connection connection;

    public TypeDaoImpl() {
        super(Type.class);
    }

    public TypeDaoImpl(Connection connection) {
        super(Type.class);
        this.connection = connection;
    }

    @Override
    public int addType(Type type) throws Exception {
        return new QueryRunner(DBUtil.getDataSource()).update(
                TypeSql.INSERT_TYPE,
                type.getFid(), type.getTypeName(), type.getIsParent());
    }

    @Override
    public int updateType(Type type) throws Exception {
        return new QueryRunner(DBUtil.getDataSource()).update(TypeSql.UPDATE_TYPE, type.getFid(), type.getTypeName(), type.getIsParent(), type.getTypeStatus(), type.getId());
    }

}
