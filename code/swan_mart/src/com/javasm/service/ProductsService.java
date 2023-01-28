package com.javasm.service;

import com.javasm.base.BaseService;
import com.javasm.bean.ProductsModel;
import com.javasm.dao.ProductsDAO;

import java.lang.reflect.Method;
import java.util.List;

public class ProductsService extends BaseService<ProductsModel> {
    public ProductsService() {
        super(new ProductsDAO());
    }


    public void selectProductById(List<String> inputs) throws Exception {
        if (checkInputs(inputs)) {
            Method method = dao.getClass().getMethod("selectProductById", Integer.class);
            results.add((ProductsModel) method.invoke(dao, Integer.valueOf(inputs.get(0))));
        }
    }

    public void selectProductByName(List<String> inputs) throws Exception {
        if (checkInputs(inputs)) {
            Method method = dao.getClass().getMethod("selectProductByName", String.class);
            results.add((ProductsModel) method.invoke(dao, inputs.get(0)));
        }
    }

    public void selectAllProducts(List<String> inputs) throws Exception {
        Method method = dao.getClass().getMethod("selectAllProducts",Integer.class);
        results = (List<ProductsModel>) method.invoke(dao,Integer.valueOf(inputs.get(0)));
    }

    public void insertProduct(List<String> inputs) throws Exception {
        if(checkInputs(inputs)){
            Method method = dao.getClass().getMethod("insertProduct",ProductsModel.class);
            ProductsModel product = new ProductsModel();
            product.setName(inputs.get(0));
            product.setTypeId(Integer.valueOf(inputs.get(1)));
            product.setInventory(Integer.valueOf(inputs.get(2)));
            product.setPrice(Double.valueOf(inputs.get(3)));
            product.setState(Byte.valueOf(inputs.get(4)));
            product.setDiscount(0.9);
            product.setSpecs(inputs.get(5));
            method.invoke(dao,product);
            results.add(product);
        }
    }

    public void updateProduct(List<String> inputs) throws Exception {
        if(checkInputs(inputs)){
            Method method = dao.getClass().getMethod("insertProduct",ProductsModel.class);
            ProductsModel product = new ProductsModel();
            product.setId(Integer.valueOf(inputs.get(0)));
            product.setName(inputs.get(1));
            product.setTypeId(Integer.valueOf(inputs.get(2)));
            product.setInventory(Integer.valueOf(inputs.get(3)));
            product.setPrice(Double.valueOf(inputs.get(4)));
            product.setState(Byte.valueOf(inputs.get(5)));
            product.setDiscount(0.9);
            product.setSpecs(inputs.get(6));
            method.invoke(dao,product);
            results.add(product);
        }
    }
}
