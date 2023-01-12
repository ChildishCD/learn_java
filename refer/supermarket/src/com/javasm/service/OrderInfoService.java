package com.javasm.service;

import com.javasm.bean.Member;
import com.javasm.common.ServerResponse;

import java.math.BigDecimal;

/**
 * @author: Lisa
 * @className: OrderInfoService
 * @description:
 * @date: 2021/6/15 16:16
 * @version: 0.1
 * @since: 1.8
 */
public interface OrderInfoService {
    ServerResponse pay(BigDecimal totalMoney, Member member, int payType);
}
