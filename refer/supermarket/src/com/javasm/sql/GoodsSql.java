package com.javasm.sql;

import com.javasm.common.CodeEnum;

/**
 * @author: Lisa
 * @className: GoodsSql
 * @description:
 * @date: 2021/6/15 11:11
 * @version: 0.1
 * @since: 1.8
 */
public interface GoodsSql {
    String ADD_GOOD =
            "insert into goods (good_name, type_id, store, good_status, discount, price) values (?,?,?,"+CodeEnum.GOOD_ON_SALE.getCode() +",10,?)";
    String UPDATE_GOOD_BY_ID = "update goods set good_name=?, type_id=?, store=?, good_status=?, discount=?, price=? where id=?";
}
