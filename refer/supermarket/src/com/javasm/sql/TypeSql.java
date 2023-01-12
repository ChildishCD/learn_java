package com.javasm.sql;

/**
 * @author: Lisa
 * @className: TypeSql
 * @description:
 * @date: 2021/6/15 10:24
 * @version: 0.1
 * @since: 1.8
 */
public interface TypeSql {
    String INSERT_TYPE = "INSERT INTO type (fid, type_name, is_parent, type_status) VALUES (?,?,?,1)";
    String UPDATE_TYPE = "update type set fid=?, type_name=?, is_parent=?, type_status=? where  id=?";
}
