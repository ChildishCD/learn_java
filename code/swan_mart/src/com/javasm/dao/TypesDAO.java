package com.javasm.dao;

import com.javasm.base.BaseDAO;
import com.javasm.bean.ProductsModel;
import com.javasm.bean.ProductsTypesModel;

import java.util.List;

public class TypesDAO extends BaseDAO<ProductsTypesModel> {
    public ProductsTypesModel selectTypeById(Integer id) {
        return super.selectObjectById(id);
    }

    public List<ProductsTypesModel> selectTypeByName(String name) {
        String sql = "SELECT * FROM " + getTableName() + " WHERE name like ?";
        name = "%" + name + "%";
        return selectList(sql, name);
    }

    public List<ProductsTypesModel> selectAllTypes(Integer start){
        return super.selectListByPage(start,null);
    }

    public void insertType(ProductsTypesModel type){
        super.saveOrUpdate(type);
    }
}
