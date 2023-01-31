package com.javasm.service;

import com.javasm.base.BaseService;
import com.javasm.bean.ProductsModel;
import com.javasm.bean.ProductsTypesModel;
import com.javasm.dao.ProductsDAO;
import com.javasm.dao.TypesDAO;

import java.lang.reflect.Method;
import java.util.List;

public class TypesService extends BaseService<ProductsTypesModel> {
    public TypesService() {
        super(new TypesDAO());
    }

    public void selectTypeById(List<String> inputs) throws Exception {
        if (checkInputs(inputs)) {
            Method method = dao.getClass().getMethod("selectTypeById", Integer.class);
            results.add((ProductsTypesModel) method.invoke(dao, Integer.valueOf(inputs.get(0))));
        }
    }

    public void selectTypeByName(List<String> inputs) throws Exception {
        if (checkInputs(inputs)) {
            Method method = dao.getClass().getMethod("selectTypeByName", String.class);
            results = (List<ProductsTypesModel>) method.invoke(dao, inputs.get(0));
        }
    }

    public void selectAllTypes(List<String> inputs) throws Exception {
        Method method = dao.getClass().getMethod("selectAllTypes",Integer.class);
        results = (List<ProductsTypesModel>) method.invoke(dao,Integer.valueOf(inputs.get(0)));
    }

    private void insertOne(List<String> inputs,ProductsTypesModel type)throws Exception{
        Method method = dao.getClass().getMethod("insertType",ProductsTypesModel.class);
        type.setId(Integer.valueOf(inputs.get(0)));
        type.setPid(Integer.valueOf(inputs.get(1)));
        type.setIsparent(Byte.valueOf(inputs.get(2)));
        type.setName(inputs.get(3));
        method.invoke(dao,type);
        if(!checkResults()){
            results.add(type);
        }
    }

    public void insertType(List<String> inputs) throws Exception {
        if(checkInputs(inputs)){
            insertOne(inputs,new ProductsTypesModel());
        }
    }

    public void updateType(List<String> inputs) throws Exception {
        if(checkInputs(inputs)){
            insertOne(inputs,results.get(0));
        }
    }

}
