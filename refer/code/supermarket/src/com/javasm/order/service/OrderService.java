package com.javasm.order.service;

import com.javasm.buy.vo.GoodsCarVO;
import com.javasm.order.dao.OrderDAO;
import com.javasm.order.dao.OrderInfoDAO;
import com.javasm.order.model.SmOrderInfoModel;
import com.javasm.order.model.SmOrderModel;
import com.javasm.order.vo.RankVO;
import com.javasm.utils.ParameterUtil;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderService {

    private OrderDAO orderDAO = new OrderDAO();
    private OrderInfoDAO orderInfoDAO = new OrderInfoDAO();

    public void saveOrder(SmOrderModel orderModel, Map<Integer, GoodsCarVO> buyCar){
        if (!ParameterUtil.check(orderModel,buyCar)){
            return;
        }
        final Map<Integer, GoodsCarVO> car = buyCar;
        Integer orderId = orderDAO.insertOrderGenKey(orderModel);
        //根据orderid，添加详情
        List<SmOrderInfoModel> infoList = Collections.synchronizedList( new ArrayList<>());
        car.forEach((k,v)->{
            SmOrderInfoModel infoModel = new SmOrderInfoModel();
            infoModel.setOrderId(orderId);
            infoModel.setGoodsId(k);
            infoModel.setGoodsNum(v.getNum());
            infoList.add(infoModel);
        });
        orderInfoDAO.insertOrderInfoByList(infoList);
    }

    //订单id 或者 商品id
    public List<SmOrderModel> queryListById(Integer id){
        List<SmOrderModel> list = new ArrayList<>();
        //先假设id是订单id
        //先去order表
        SmOrderModel orderModel = orderDAO.selectObjectById(id);
        if (orderModel !=null){
            //查询订单信息，根据订单信息查询详情
            List<SmOrderInfoModel> infoList = orderInfoDAO.selectListByOrderId(id);
            orderModel.setOrderInfoList(infoList);
            list.add(orderModel);
        }
        //然后假设id是商品id,查询详情，然后获得订单id，查询订单集合
        List<SmOrderInfoModel> infoList = orderInfoDAO.selectListByGoodsId(id);
        if (infoList!=null && infoList.size()>0){
            //获取所有的订单id
            List<Integer> ids = infoList.stream().map(SmOrderInfoModel::getOrderId).collect(Collectors.toList());
            if (ids != null){
                //根据订单id集合，查询到所有符合条件的订单
                List<SmOrderModel> orderList = orderDAO.selectOrderList(ids);
                if (orderList != null){
                    //infoList 根据orderid分组
                    Map<Integer, List<SmOrderInfoModel>> map = infoList.stream().collect(Collectors.groupingBy(SmOrderInfoModel::getOrderId));
                    orderList.forEach(o ->{
                        o.setOrderInfoList(map.get(o.getId()));
                    });
                    list.addAll(orderList);
                    //去重
                    list = list.stream().distinct().collect(Collectors.toList());
                }
            }
        }
        return list;
    }

    public List<RankVO> rank(Integer month,Integer type){
        if (!ParameterUtil.check(month,type)){
            return null;
        }
        //要求按月份及按商品类别统计销量前10的商品及总销量
        /*
            翻译翻译：统计销量，所以查询订单详情
            查询订单详情表sm_order_info,goods_id进行groupBy,然后sum销售数量goods_num
            没有对应的类接收，创建类 RankVO
         */
        //默认获取今年 LocalDateTime.now().get(ChronoField.YEAR)
        return orderDAO.selectGoodsRank(LocalDateTime.now().get(ChronoField.YEAR), month, type);
    }
}
