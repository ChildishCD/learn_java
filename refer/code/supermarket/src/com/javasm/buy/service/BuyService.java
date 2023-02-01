package com.javasm.buy.service;

import com.javasm.buy.vo.GoodsCarVO;
import com.javasm.goods.model.GoodsModel;
import com.javasm.goods.service.GoodsService;
import com.javasm.member.dao.MemberDAO;
import com.javasm.member.model.SmMemberModel;
import com.javasm.order.model.SmOrderModel;
import com.javasm.order.service.OrderService;
import com.javasm.utils.ExecutorFactory;
import com.javasm.utils.ParameterUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

public class BuyService {


    private GoodsService goodsService = new GoodsService();
    private MemberDAO memberDAO = new MemberDAO();
    private OrderService orderService = new OrderService();

    public GoodsCarVO queryByGid(Integer id){
        GoodsModel goodsModel = goodsService.queryById(id);
        GoodsCarVO car = new GoodsCarVO();
        car.setGoods(goodsModel);
        return car;
    }
    public SmMemberModel checkMember(String username,String password){
        if (!ParameterUtil.check(username,password)){
            return null;
        }
        return memberDAO.checkMember(username,password);
    }

    public boolean cashBuy(Map<Integer, GoodsCarVO> buyCar){
        if (!ParameterUtil.check(buyCar)){
            return false;
        }
        final Map<Integer, GoodsCarVO> car = buyCar;
        //
        ExecutorFactory.execute(()->{
            Double totalPrice = totalPrice(car);
            long balance = totalBalance(totalPrice);
            SmOrderModel orderModel = new SmOrderModel(0,balance,0);
            orderService.saveOrder(orderModel,car);
        });
        return true;
    }

    public  boolean memberBuy(SmMemberModel memberModel, Map<Integer, GoodsCarVO> buyCar){
        if (!ParameterUtil.check(memberModel,buyCar)){
            return false;
        }
        final Map<Integer, GoodsCarVO> car = buyCar;
        //获取账户余额，计算购物车总价
        Double totalPrice = totalPrice(car);
        if (totalPrice < memberModel.getBalance()){
            //商品价格 低于 用户余额
            //扣钱 账户余额单位是分
            long balance = totalBalance(totalPrice);
            memberDAO.pay(memberModel.getId(),-balance);
            //扣完钱之后，购物操作已经结束，生成订单的操作，可以稍后生成，没有必要让用户等待
            //订单的存储 放到多线程中操作
            ExecutorFactory.execute(()->{
                SmOrderModel orderModel = new SmOrderModel(memberModel.getId(),balance,1);
                orderService.saveOrder(orderModel,car);
            });
            return true;
        }
        return false;//商品价格 高于 用户余额
    }

    public Long totalBalance(Double totalPrice){
        Double price = totalPrice * 100;
        long balance = price.longValue();//最终要减掉的余额数
        return balance;
    }
    public Double totalPrice(Map<Integer, GoodsCarVO> buyCar){
        //计算购物车总价
        //原子操作 自旋锁
        AtomicReference<Double> total = new AtomicReference<>(0.0);
        if (buyCar != null){
            buyCar.forEach((k,v)->{
                total.updateAndGet(v1 -> v1 + v.getGoods().getRealPrice());
            });
        }
        return total.get();
    }
}
