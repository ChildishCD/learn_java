package com.javasm.dao;

import com.javasm.base.BaseDAO;
import com.javasm.bean.ProductsModel;

import java.util.List;

public class ProductsDAO extends BaseDAO<ProductsModel> {

    public ProductsModel selectProductById(Integer id) {
        return super.selectObjectById(id);
    }

    public ProductsModel selectProductByName(String name) {
        String sql = "SELECT * FROM " + getTableName() + " WHERE name like ?";
        name = "%" + name + "%";
        return selectObject(sql, name);
    }

    public List<ProductsModel> selectAllProducts(Integer start){
        return super.selectListByPage(start,null);
    }

    public void insertProduct(ProductsModel product){
        super.saveOrUpdate(product);
    }
}
