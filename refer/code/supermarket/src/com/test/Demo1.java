package com.test;

import com.javasm.base.BaseModel;
import com.javasm.goods.model.GoodsModel;

import java.util.*;

public class Demo1 {

    //where 条件 的字段 加索引 能增加 查询的性能
    //where 条件  概率大的/能筛选出更少数据的条件  放在前面
    //主键 自带索引
    // where id = 10 and name = 'xiaoming' and age= 12
    //不能把 所有的字段都加索引

    {
        //new对象的时候
    }

    static {
        // 使用 类 就被调用了
    }

    public static void main(String[] args) throws Exception {
        BaseModel baseModel = new BaseModel();
        baseModel.id = 10;

        test5();

    }

    private static void test5() {
        List<GoodsModel> list = new ArrayList<>();
        list.sort((o1, o2) -> o1.getPrice().compareTo(o2.getPrice()));
        list.sort(new Comparator<GoodsModel>() {
            @Override
            public int compare(GoodsModel o1, GoodsModel o2) {
                return 0;
            }
        });
       // list.stream().sorted();
    }

    private static void test4() {
        final GoodsModel goodsModel = new GoodsModel();
        goodsModel.setGoodsName("橘子");
        GoodsModel goodsClone = goodsModel.clone();
        new Thread(()->{

            //这里 必须 输出 橘子
            System.out.println(goodsClone.getGoodsName());
        }).start();
        goodsModel.setGoodsName("苹果");
        //goodsModel = new GoodsModel();//不能 重新 new对象
        System.out.println(goodsModel.getGoodsName());//苹果
    }

    private static void test3(BaseModel baseModel)  {
        Integer i = 109;
        int j = i;
        try {
            throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //BaseModel 父类
        //=  赋值运算符   把 右边的值  给 左边
        //baseModel 变量
        //new BaseModel(); 值
        // new GoodsModel(); 值
        //GoodsModel goodsModel = (GoodsModel) baseModel;
        /*GoodsModel goodsModel = new GoodsModel();
        goodsModel.setId(baseModel.id);*/

    }

    //final 最终的，修饰什么 都不能再改了
    private static void test2(final  int i,int j) {
        //i = 10;
        j = 10;
        final   int kkkk = 10;
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
        //kkkk = 20;
        String[] a = {"java","html"};
        String[] b = {"sql","css"};
        String[][] aa = {a,b};
        List<String> list = Arrays.asList("java", "html");


    }

    private static void test1(){
        zhanagsan:for(int i=0;i<10;i++){
            lisi:for (int j=0;j<10;j++){
                break lisi;
            }
        }
        List<String> list = new ArrayList<>();
        list.sort(String::compareTo);
        ArrayList<String> list2 = (ArrayList<String>) list;
        for (int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }
        for (String s : list){
            System.out.println(s);
        }
        list.forEach(System.out::println);
        list.forEach(s -> {
            int a = 1+2;
            System.out.println(s+a);
        });
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        int i=0;
        while (i<list.size()){
            System.out.println(list.get(i));
            i++;
        }
        /*do {
            //一定会执行一次的
        }while (布尔值);*/
    }
}
