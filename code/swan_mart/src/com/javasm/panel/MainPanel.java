package com.javasm.panel;

import com.javasm.base.BasePanel;
import com.javasm.bean.RoleEnum;

public class MainPanel extends BasePanel {

    //TODO:利用annotation 直接获取方法名或菜单名，简化basicPanel 的输入参数
    private MainPanel() {
    }

    private static MainPanel mainPanel;

    public static MainPanel getInstance() {
        if (mainPanel == null) {
            mainPanel = new MainPanel();
        }
        return mainPanel;
    }

    public void star() {
        listPanel("登录",mainPanel, new String[][]{
                {"我是管理员\uD83D\uDD11", "adminLogin"},
                {"我是收银员\uD83D\uDD11", "cashierLogin"}
        });
    }

    private void cashierLogin() {
        System.out.println("请输入收银员密钥:");
        if (scanner.next().equals(RoleEnum.CASHIER.getPassWord())) {
            System.out.println("用户登录/收银员面板");
            listPanel("收银员面板",new DealsPanel(naviBar), new String[][]{
                    {"购买管理", "shopping"},
                    {"订单查询", "ordersCheck"},
                    {"排行统计", "productsRanking"}
            });
        } else {
            System.out.println("收银员登录失败!");
        }
    }

    private void adminLogin() {
        System.out.println("请输入管理员密钥:");
        if (scanner.next().equals(RoleEnum.ADMIN.getPassWord())) {
            listPanel("管理员面板",mainPanel, new String[][]{
                    {"商品类型管理", "productsTypesOperation"},
                    {"商品管理", "productsOperation"},
                    {"会员管理", "membersOperation"}
            });
        } else {
            System.out.println("管理员登录失败!");
        }
    }

    private void productsTypesOperation() {
        listPanel("商品类型管理",new TypesPanel(naviBar), new String[][]{
                {"查询商品类型", "selectType"},
                {"添加/修改商品类型", "updateType"},
                {"删除商品类型", "deleteType"}
        });
    }

    private void productsOperation() {
        listPanel("商品管理",new ProductsPanel(naviBar), new String[][]{
                {"查询商品", "selectProduct"},
                {"添加/修改商品", "updateProduct"},
                {"删除商品", "deleteProduct"}
        });
    }

    private void membersOperation() {
        listPanel("会员管理",new MembersPanel(naviBar), new String[][]{
                {"查询会员", "selectMember"},
                {"添加/修改会员", "updateMember"},
                {"删除会员", "deleteMember"},
                {"会员充值", "chargingMember"}
        });
    }

}
