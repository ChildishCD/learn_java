package com.javasm.dao;

import java.util.List;
import java.util.Map;

/**
 * @author: Lisa
 * @className: BaseDao
 * @description:
 * @date: 2021/6/15 11:24
 * @version: 0.1
 * @since: 1.8
 */
public interface BaseDao<T> {

    T selectOne(int id) throws Exception;

    List<T> selectAll() throws Exception;

    List<T> selectByPrams(Map<String, Object> params) throws Exception;

    int deleteOne(int id) throws Exception;

}
