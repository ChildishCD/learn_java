package com.javasm.service;

import com.javasm.base.BaseService;
import com.javasm.base.BaseVO;
import com.javasm.bean.MemberOrdersModel;
import com.javasm.bean.MemberOrdersProductsModel;
import com.javasm.bean.ProductsModel;
import com.javasm.vo.RankVO;
import com.javasm.vo.ShoppingVO;
import com.javasm.dao.OrdersDAO;
import com.javasm.dao.OrdersProductsDAO;
import com.javasm.dao.ProductsDAO;
import com.javasm.utils.DateUtilJavasm;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.*;

public class DealsService extends BaseService<MemberOrdersModel> {

    @Override
    public void showResults() {
        //存放order和详情
        Map<MemberOrdersModel, List<MemberOrdersProductsModel>> ordersProducts = new TreeMap<>();
        //存放详情中出现的pid
        Set<Integer> containPIds = new TreeSet<>();
        //获得以上两个
        for (MemberOrdersModel order : results) {
            List<MemberOrdersProductsModel> orderPList = opDAO.selectOrdersProductsByOId(order.getId());
            orderPList.forEach(info -> {
                containPIds.add(info.getProductId());
            });
            ordersProducts.put(order, orderPList);
        }
        //获得详情中存在的商品
        List<ProductsModel> containPs = pDAO.selectObjectByIdList(new ArrayList<>(containPIds));
        Map<Integer, ProductsModel> containPsMap = new HashMap<>();
        containPs.forEach(p -> {
            containPsMap.put(p.getId(), p);
        });
        //输出商品详情
        for (MemberOrdersModel order : ordersProducts.keySet()) {
            System.out.println("\t|订单ID|会员ID|消费金额（元）|创建时间|支付方式|");
            System.out.printf("\t|%d|%d|%s|%s|%s|\n",
                    order.getId(),
                    order.getMemberId(),
                    order.getPriceTotal(),
                    DateUtilJavasm.timeToStringAll(order.getCreateTime()),
                    order.getPaymentType()
            );
            List<MemberOrdersProductsModel> orderPList = ordersProducts.get(order);
            if (orderPList != null && !orderPList.isEmpty()) {
                System.out.println("\t\t------------------------------------");
                System.out.println("\t\t\t|商品ID|商品名称|商品数量|");
                orderPList.forEach(info -> {
                    ProductsModel p = containPsMap.get(info.getProductId());
                    System.out.printf("\t\t\t|%d|%s|%d|\n",
                            info.getProductId(),
                            p.getName(),
                            info.getCount()
                    );
                });
                System.out.println("\t\t------------------------------------");
            }
        }
    }

    //TODO:会员和订单两个线程
    public OrdersDAO oDAO = new OrdersDAO();
    public ProductsDAO pDAO = new ProductsDAO();
    public OrdersProductsDAO opDAO = new OrdersProductsDAO();
    public Map<Integer, ShoppingVO> shoppingCar = ShoppingVO.shotCar();

    public void selectOrderById(List<String> inputs) throws Exception {
        if (checkInputs(inputs)) {
            Method method = oDAO.getClass().getMethod("selectOrderById", Integer.class);
            results.add((MemberOrdersModel) method.invoke(oDAO, Integer.valueOf(inputs.get(0))));
        }
    }

    public void selectOrderByPId(List<String> inputs) throws Exception {
        if (checkInputs(inputs)) {
            Method method = oDAO.getClass().getMethod("selectOrderByPId", Integer.class);
            results = (List<MemberOrdersModel>) method.invoke(oDAO, Integer.valueOf(inputs.get(0)));
        }
    }

    public void selectOrderByMId(List<String> inputs) throws Exception {
        if (checkInputs(inputs)) {
            Method method = oDAO.getClass().getMethod("selectOrderByMId", Integer.class);
            results = (List<MemberOrdersModel>)  method.invoke(oDAO, Integer.valueOf(inputs.get(0)));
        }
    }


    public void insertOrder(MembersService mService) throws Exception {
        int mId = mService.results.get(0).getId();
        byte payment = (byte) (mId == 1 ? 0 : 1);
        double price = getTotalPrice();
        MemberOrdersModel order = new MemberOrdersModel(null, mId, price, LocalDateTime.now(), payment);
        int oId = oDAO.insertOrder(order);
        List<MemberOrdersProductsModel> orderProducts = new ArrayList<>();
        List<ProductsModel> products = new ArrayList<>();
        for (ShoppingVO s : shoppingCar.values()) {
            ProductsModel p = s.getProduct();
            orderProducts.add(new MemberOrdersProductsModel(null, oId, p.getId(), s.getCount()));
            Integer inventory = p.getInventory();
            p.setInventory(inventory - s.getCount());
            products.add(p);
        }
        pDAO.updateList(products);
        opDAO.insertOrderProducts(orderProducts);
        if (payment == 1) {
            double cost = -price;
            mService.addBalance(Arrays.asList(Double.toString(cost)));
        }

    }

    public Double getTotalPrice() {
        Double price = 0.0;
        for (ShoppingVO s : shoppingCar.values()) {
            price += s.getPrice();
        }
        return price;
    }

    public void selectRank(List<String> inputs) throws Exception {
        if (checkInputs(inputs)) {
            Method method = oDAO.getClass().getMethod("selectRank");
            voResults = (List<BaseVO>) method.invoke(oDAO);
        }
    }

    public void selectRankByMonth(List<String> inputs) throws Exception {
        if (checkInputs(inputs)) {
            Method method = oDAO.getClass().getMethod("selectRankByMonth",Integer.class);
            voResults = (List<BaseVO>) method.invoke(oDAO, Integer.valueOf(inputs.get(0)));
        }
    }

    public void selectRankByType(List<String> inputs) throws Exception {
        if (checkInputs(inputs)) {
            Method method = oDAO.getClass().getMethod("selectRankByType",Integer.class);
            voResults = (List<BaseVO>) method.invoke(oDAO, Integer.valueOf(inputs.get(0)));
        }
    }



}
