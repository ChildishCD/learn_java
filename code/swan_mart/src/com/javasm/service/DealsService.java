package com.javasm.service;

import com.javasm.base.BaseService;
import com.javasm.bean.*;
import com.javasm.dao.OrdersDAO;
import com.javasm.dao.OrdersProductsDAO;
import com.javasm.dao.ProductsDAO;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DealsService extends BaseService<MemberOrdersModel> {
    public OrdersDAO oDAO = new OrdersDAO();
    public ProductsDAO pDAO = new ProductsDAO();
    public OrdersProductsDAO opDAO = new OrdersProductsDAO();
    public Map<Integer, Shopping> shoppingCar = Shopping.shotCar();

    public void selectOrderById(List<String> inputs) throws Exception {
        if (checkInputs(inputs)) {
            Method method = oDAO.getClass().getMethod("selectTypeById", Integer.class);
            results.add((MemberOrdersModel) method.invoke(oDAO, Integer.valueOf(inputs.get(0))));
        }
    }
    public void selectOrderByPId(List<String> inputs) throws Exception {
        if (checkInputs(inputs)) {
            Method method = oDAO.getClass().getMethod("selectOrderByPId", Integer.class);
            results.add((MemberOrdersModel) method.invoke(oDAO, Integer.valueOf(inputs.get(0))));
        }
    }
    public void selectOrderByMId(List<String> inputs) throws Exception {
        if (checkInputs(inputs)) {
            Method method = oDAO.getClass().getMethod("selectOrderByMId", Integer.class);
            results.add((MemberOrdersModel) method.invoke(oDAO, Integer.valueOf(inputs.get(0))));
        }
    }


    public void insertOrder(MembersService mService) throws Exception {
        int mId = mService.results.get(0).getId();
        byte payment = (byte) (mId == 1 ? 0 : 1);
        double price = getTotalPrice();
        MemberOrdersModel order =  new MemberOrdersModel(null,mId,price, LocalDateTime.now(),payment);
        int oId = oDAO.insertOrder(order);
        List<MemberOrdersProductsModel> orderProducts = new ArrayList<>();
        List<ProductsModel> products = new ArrayList<>();
        for(Shopping s:shoppingCar.values()){
            ProductsModel p = s.getProduct();
            orderProducts.add(new MemberOrdersProductsModel(null,oId,p.getId(),s.getCount()));
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
        for (Shopping s : shoppingCar.values()) {
            price += s.getPrice();
        }
        return price;
    }

}
