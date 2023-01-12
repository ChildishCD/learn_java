package com.javasm.dao;

import com.javasm.bean.Type;

/**
 * @author: Lisa
 * @className: TypeDao
 * @description:
 * @date: 2021/6/15 10:16
 * @version: 0.1
 * @since: 1.8
 */
public interface TypeDao extends BaseDao<Type> {

    //List<Type> selectAllType() throws Exception;

    int addType(Type type) throws Exception;

    int updateType(Type type) throws Exception;

    // List<Type> selectChildType(int pid) throws Exception;
}
