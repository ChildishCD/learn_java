package com.javasm.service.impl;

import com.javasm.bean.Goods;
import com.javasm.bean.Member;
import com.javasm.bean.OrderInfo;
import com.javasm.common.CodeEnum;
import com.javasm.common.ServerResponse;
import com.javasm.common.ShoppingCar;
import com.javasm.dao.GoodsDao;
import com.javasm.dao.MemberDao;
import com.javasm.dao.OrderInfoDao;
import com.javasm.dao.impl.GoodsDaoImpl;
import com.javasm.dao.impl.MemberDaoImpl;
import com.javasm.dao.impl.OrderInfoDaoImpl;
import com.javasm.service.OrderInfoService;
import com.javasm.util.DBUtil;
import com.javasm.vo.GoodVO;
import org.apache.commons.dbutils.DbUtils;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Map;
import java.util.Set;

/**
 * @author: Lisa
 * @className: OrderInfoServiceImpl
 * @description:
 * @date: 2021/6/15 16:16
 * @version: 0.1
 * @since: 1.8
 */
public class OrderInfoServiceImpl implements OrderInfoService {
    //订单表生成  获得自增的id    订单详情 (订单id)
    //库存肯定要减   0 改为下架状态  商品表
    //会员支付: 余额
    @Override
    public ServerResponse pay(BigDecimal totalMoney, Member member, int payType) {

        Connection connection = DBUtil.getConnection();
        OrderInfoDao orderInfoDao = new OrderInfoDaoImpl(connection);
        GoodsDao goodsDao = new GoodsDaoImpl(connection);
        MemberDao memberDao = new MemberDaoImpl(connection);
        try {
            connection.setAutoCommit(false);

            long oid = orderInfoDao.addOrder(OrderInfo.builder().mid(member.getId()).payType(payType).totalMoney(totalMoney).build());
            Set<Map.Entry<Integer, GoodVO>> entrySet = ShoppingCar.car.entrySet();
            for (Map.Entry<Integer, GoodVO> entry : entrySet) {

                GoodVO goodVO = entry.getValue();
                Goods goods = goodVO.getGoods();
                Integer buyNum = goodVO.getBuyNum();
                int num = goods.getStore() - buyNum;
                goods.setStore(num);
                if (num == 0) {
                    goods.setGoodStatus(CodeEnum.GOOD_OFF_SALE.getCode());
                }
                goodsDao.updateGoodById(goods);

                orderInfoDao.addOrderDetail((int) oid, goods.getId(), buyNum);
            }

            if (member.getId() != -1) {
                member.setBalance(member.getBalance().subtract(totalMoney));
                memberDao.updateMember(member);
            }

            DbUtils.commitAndCloseQuietly(connection);
            return ServerResponse.success("订单支付成功。。。。");
        } catch (Exception e) {
            e.printStackTrace();
            DbUtils.rollbackAndCloseQuietly(connection);
        }
        return ServerResponse.error("订单支付失败。。。");
    }
}
