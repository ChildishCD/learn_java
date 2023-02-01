package com.javasm.goods.service;

import com.javasm.goods.dao.GoodsDAO;
import com.javasm.goods.model.GoodsModel;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

//处理 业务逻辑，不能有录入的操作，不能有显示信息的操作
public class GoodsService {

    private GoodsDAO goodsDAO = new GoodsDAO();
    //因为商品不能是无限多的，一个超市的产品数量不会太多，所以可以存全局变量
    private static List<GoodsModel> goodsList;
    private static Map<Integer,GoodsModel> goodsMap;

    //删除
    public void deleteGoods(Integer id){
        if (id !=null){
            //真删除、物理删除
            //goodsDAO.deleteById(id);
            //假删除、逻辑删除
            GoodsModel goodsModel = queryById(id);
            if (goodsModel != null){
                goodsModel.setIsDel(1);
                goodsDAO.saveOrUpdate(goodsModel);
                clearGoods();//清空
            }

        }
    }
    //查询商品集合
    public List<GoodsModel> queryGoodsList(){
        if (goodsList == null){
            //如果这个全局变量没有值了，要去数据库查询
            goodsList = goodsDAO.selectGoodsList();
            goodsMap = goodsList.stream().collect(Collectors.toMap(GoodsModel::getId, Function.identity()));
        }
        return goodsList;
    }
    public GoodsModel queryById(Integer id){
        if (goodsMap == null){
            queryGoodsList();
            //确保goodsMap数据正确，就可以不用去数据库查询了
            //return goodsDAO.selectObjectById(id);
        }
        return goodsMap.get(id);

    }

    public void edit(Integer id, String name, int type,
                     Double price, Integer num,
                     Integer state, Integer discount) {
        GoodsModel goodsModel = new GoodsModel();
        //添加&修改
        if (id != null){
            goodsModel = queryById(id);
        }
        goodsModel.setGoodsName(name);
        goodsModel.setGoodsType(type);
        goodsModel.setPrice(price);
        goodsModel.setInventory(num);
        goodsModel.setDiscount(discount);
        goodsModel.setState(state);
        goodsDAO.saveOrUpdate(goodsModel);
        clearGoods();//清空
    }

    //清空静态变量，等待重新赋值
    private void clearGoods(){
        goodsList = null;
        goodsMap = null;
    }
}
