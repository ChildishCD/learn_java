package com.javasm.panel;

import com.javasm.base.BasePanel;
import com.javasm.banner.AdBanner;
import com.javasm.service.TypesService;

import java.util.Stack;

public class TypesPanel extends BasePanel {
    private TypesService typesService = new TypesService();

    public TypesPanel(Stack<String> navi) {
        this.naviBar = navi;
    }

    public void selectType() {
        while (go.equals("Y") || go.equals("y")){
            AdBanner.printSeparator();
            printNaviBanner();
            System.out.println("请输入商品类型id");
            String id = scanner.next();
            if(id.matches("^\\d+$")){
                //TODO
                System.out.println(typesService.selectTypeById(Integer.valueOf(id)));
            }
            //询问是否结束当前面板
            System.out.println("您希望继续吗: (Y/N)");
            go = scanner.next();
        }
        initPanel();
    }

    public void updateType() {

    }

    public void deleteType() {

    }
}
