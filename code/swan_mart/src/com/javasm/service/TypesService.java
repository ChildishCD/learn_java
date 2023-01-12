package com.javasm.service;

import com.javasm.bean.ProductsTypesModel;
import com.javasm.dao.TypesDAO;

import java.util.List;

public class TypesService {
    private TypesDAO typesDAO = new TypesDAO();
    //添加
    public void insertTypes(ProductsTypesModel typesModel){
        if (typesModel!=null && typesModel.getId() == null){
            typesDAO.saveOrUpdate(typesModel);
        }
    }
    //修改
    public void updateTypes(ProductsTypesModel typesModel){
        if (typesModel!=null && typesModel.getId()!=null){
            typesDAO.saveOrUpdate(typesModel);
        }
    }
    //删除
    public void deleteTypes(Integer id){
        if (id !=null){
            typesDAO.deleteById(id);
        }
    }

    public ProductsTypesModel selectTypeById(Integer id){
        ProductsTypesModel typesModel = null;
        if(id != null){
            typesModel = typesDAO.selectObjectById(id);
        }
        return typesModel;
    }
    //查询集合
//    public List<ProductsTypesModel> queryTypesList(){
//        return typesDAO.selectListByPage(0,100);
//    }

}
