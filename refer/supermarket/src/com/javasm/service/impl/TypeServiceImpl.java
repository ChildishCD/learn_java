package com.javasm.service.impl;

import com.javasm.bean.Goods;
import com.javasm.bean.Type;
import com.javasm.common.CodeEnum;
import com.javasm.common.ServerResponse;
import com.javasm.dao.GoodsDao;
import com.javasm.dao.TypeDao;
import com.javasm.dao.impl.GoodsDaoImpl;
import com.javasm.dao.impl.TypeDaoImpl;
import com.javasm.service.TypeService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Lisa
 * @className: TypeServiceImpl
 * @description:
 * @date: 2021/6/15 10:26
 * @version: 0.1
 * @since: 1.8
 */
public class TypeServiceImpl implements TypeService {


    private TypeDao typeDao = new TypeDaoImpl();
    private GoodsDao goodDao = new GoodsDaoImpl();

    private static Map<String, Object> params;

    static {
        params = new HashMap<>(16);
    }

    @Override
    public List<Type> selectAllType() {
        try {
            params.clear();
            params.put("type_status", 1);
            return typeDao.selectByPrams(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ServerResponse addType(Type type) {
        try {

            Type parentType = typeDao.selectOne(type.getFid());
            if (parentType.getIsParent().equals(CodeEnum.NOT_PARENT_TYPE.getCode().toString())) {
                parentType.setIsParent(CodeEnum.PARENT_TYPE.getCode().toString());
                typeDao.updateType(parentType);
            }
            if (typeDao.addType(type) == 1) {
                return ServerResponse.success();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ServerResponse.error();
    }

    @Override
    public ServerResponse deleteTypeById(int typeId) {
        try {
            Type type = typeDao.selectOne(typeId);
            //判断有没有子级  select * from type where pid =?
            params.clear();
            params.put("fid", type.getId());
            List<Type> typeList = typeDao.selectByPrams(params);
            if (!typeList.isEmpty()) {
                return ServerResponse.error("当前父级下有子级类型，无法删除");
            }
            params.clear();
            params.put("type_id", type.getId());
            List<Goods> goodsList = goodDao.selectByPrams(params);
            if (!goodsList.isEmpty()) {
                return ServerResponse.error("当前类型下有商品关联，无法删除");
            }
            //可以删除
            int i = typeDao.deleteOne(type.getId());
            if (i < 0) {
                return ServerResponse.error();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ServerResponse.success();
    }
}
