package com.javasm.panel;

import com.javasm.base.BasePanel;
import com.javasm.service.ProductsService;
import com.javasm.service.TypesService;

import java.util.Stack;

public class TypesPanel extends BasePanel {
    public TypesPanel(BasePanel prePanel) {
        super(prePanel, new ProductsService());
    }

    public void selectType() {
//        while (go.equals("Y") || go.equals("y")){
//            AdBanner.printSeparator();
//            printNaviBanner();
//            System.out.println("请输入商品类型id");
//            String id = scanner.next();
//            if(id.matches("^\\d+$")){
//                //TODO:查询后,显示树状面板,包含子商品类型
//                System.out.println(typesService.selectTypeById(Integer.valueOf(id)));
//            }
//            //询问是否结束当前面板
//            System.out.println("您希望继续吗: (Y/N)");
//            go = scanner.next();
//        }
//        initPanel();
    }

    public void updateType() {

    }

    public void insertType() {

    }

    public void deleteType() {

    }
}
