package com.javasm.common;

import lombok.Getter;


@Getter
public enum CodeEnum {

    SUCCESS("SUCCESS", 200),
    ERROR("ERROR", 500),


    PARENT_TYPE("父级类型", 1),
    NOT_PARENT_TYPE("不是父级类型", 0),

    GOOD_ON_SALE("在售", 1),
    GOOD_OFF_SALE("下架", 2),
    GOOD_DELETE("删除", 3),

    CASH_PAY("现金支付", 1),
    CARD_PAY("余额支付", 2),

    VISIT("游客", -1);

    private String msg;
    private Integer code;

    CodeEnum(String msg, Integer code) {
        this.msg = msg;
        this.code = code;
    }
}
