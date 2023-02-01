package com.javasm.goods.service;

import com.javasm.goods.dao.GoodsTypeDAO;
import com.javasm.goods.model.GoodsTypeModel;
import com.javasm.utils.ParameterUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GoodsTypeService {
    private GoodsTypeDAO goodsTypeDAO = new GoodsTypeDAO();
    //查询出来的商品分类数据，放到这里，如果有数据就不需要再去数据库查询了
    //这种思想不能存放大量数据，容易内存溢出
    private static List<GoodsTypeModel> goodsTypeList;
    private static Map<Integer, GoodsTypeModel> allGoodsTypeMap;
    private static Map<Integer, GoodsTypeModel> firstGoodsTypeMap;

    public void addType(String name,Integer pid){
        if (!ParameterUtil.check(name,pid)){
            return;
        }
        GoodsTypeModel goodsTypeModel = new GoodsTypeModel(pid,name);
        goodsTypeModel.setCreateTime(LocalDateTime.now());
        goodsTypeDAO.saveOrUpdate(goodsTypeModel);
        //数据发生变化，全局记录数据的集合已经不准确，可以选择删除 或者 更新数据
        changeStaticValue();
    }
    public void updateType(Integer id,Integer pid,String name){
        if (!ParameterUtil.check(id,pid,name)){
            return;
        }
        GoodsTypeModel goodsTypeModel = queryTypeById(id);
        goodsTypeModel.setPid(pid);
        goodsTypeModel.setTypeName(name);
        goodsTypeModel.setUpdateTime(LocalDateTime.now());
        goodsTypeDAO.saveOrUpdate(goodsTypeModel);
        //数据发生变化，全局记录数据的集合已经不准确，可以选择删除 或者 更新数据
        changeStaticValue();
    }
    public void deleteType(Integer id){
        if (!ParameterUtil.check(id)){//如果不合格返回
            return;
        }
        goodsTypeDAO.deleteById(id);
        //数据发生变化，全局记录数据的集合已经不准确，可以选择删除 或者 更新数据
        changeStaticValue();
    }
    //通过id  查询 商品类别
    public GoodsTypeModel queryTypeById(Integer id){
        if (ParameterUtil.check(id)){//改了下逻辑，如果合格查询
            if (allGoodsTypeMap == null){
                //给这个map一个初始数据
                queryTypeList();
            }
            //从存储着所有数据的map中 获取对象
            //先从1级分类中获取数据，带二级列表
            GoodsTypeModel goodsTypeModel = firstGoodsTypeMap.get(id);
            //如果查不到，再去拥有全部数据的map中查
            if (goodsTypeModel == null){
                goodsTypeModel = allGoodsTypeMap.get(id);
            }
            return goodsTypeModel;
        }
        return null;
    }

    private void changeStaticValue(){
        goodsTypeList = null;
        allGoodsTypeMap = null;
        firstGoodsTypeMap = null;
    }
    public List<GoodsTypeModel> queryTypeList(){
        if (goodsTypeList != null){
            //如果这个静态变量中，有typeList，就不需要去数据库查询了
            return goodsTypeList;
        }
        //查询全部都 类型列表
        List<GoodsTypeModel> allList = goodsTypeDAO.selectListAll();
        //全局的商品类别 map 方便查找数据,1级2级都在一起，不区分
        allGoodsTypeMap = allList.stream().collect(Collectors.toMap(GoodsTypeModel::getId, Function.identity()));
        //筛选出 pid为0的一级类别
        List<GoodsTypeModel> firstList = allList.stream().filter(type -> type.getPid()==0).collect(Collectors.toList());
        //筛选出pid不为0的二级类别
        //全部都 删除 一级的 就是二级的
        allList.removeAll(firstList);
        //二级菜单分组，方便查找  key是二级菜单的pid，值是所有pid下的集合
        Map<Integer, List<GoodsTypeModel>> childMap = allList.stream().collect(Collectors.groupingBy(GoodsTypeModel::getPid));
        for (GoodsTypeModel type : firstList){
            List<GoodsTypeModel> goodsTypeModels = childMap.get(type.getId());//1级菜单的id  是2级菜单的pid
            type.setChildTypeList(goodsTypeModels);
        }
        //查询出来的集合，给全局的静态变量，方便下次使用
        goodsTypeList = firstList;
        //所有1级菜单 放这里
        firstGoodsTypeMap = firstList.stream().collect(Collectors.toMap(GoodsTypeModel::getId, Function.identity()));
        return firstList;
    }
}
